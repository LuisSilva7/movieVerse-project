package org.movieverse.movieverse_backend.service.user;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.model.Cart;
import org.movieverse.movieverse_backend.model.User;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.movieverse.movieverse_backend.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final JwtEncoder jwtEncoder;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse login(String username, String password) {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty() || !user.get().isLoginCorrect(password, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Username or password are invalid!");
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

    public String registerUser(String username, String password) {

        var userFromDb = userRepository.findByUsername(username);
        if(userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        var cart = new Cart();
        user.setCart(cart);

        userRepository.save(user);

        return "User registered successfully!";
    }
}
