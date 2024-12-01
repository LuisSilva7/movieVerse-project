package org.movieverse.movieverse_backend.service.user;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.entity.Cart;
import org.movieverse.movieverse_backend.entity.User;
import org.movieverse.movieverse_backend.exception.ResourceAlreadyExistsException;
import org.movieverse.movieverse_backend.exception.ResourceNotFoundException;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.movieverse.movieverse_backend.response.LoginResponse;
import org.movieverse.movieverse_backend.response.RegisterUserResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtEncoder jwtEncoder;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterUserResponse register(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            throw new ResourceAlreadyExistsException("Username " + username + " already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        Cart cart = new Cart();
        user.setCart(cart);

        userRepository.save(user);

        return new RegisterUserResponse(userRepository.findByUsername(username).get().getId());
    }

    public LoginResponse login(String username, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found!")));

        if(!user.get().isLoginCorrect(password, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Username and password don´t match!");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("movieVerseBackend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }
}
