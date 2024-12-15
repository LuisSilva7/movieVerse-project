package org.movieverse.movieverse_backend.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.cart.Cart;
import org.movieverse.movieverse_backend.config.security.JwtService;
import org.movieverse.movieverse_backend.email.EmailService;
import org.movieverse.movieverse_backend.email.EmailTemplateName;
import org.movieverse.movieverse_backend.exception.custom.ResourceAlreadyExistsException;
import org.movieverse.movieverse_backend.exception.custom.ResourceNotFoundException;
import org.movieverse.movieverse_backend.exception.custom.TokenExpiredException;
import org.movieverse.movieverse_backend.role.RoleRepository;
import org.movieverse.movieverse_backend.user.Token;
import org.movieverse.movieverse_backend.user.TokenRepository;
import org.movieverse.movieverse_backend.user.User;
import org.movieverse.movieverse_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email " + request.email() + " already exists!");
        }

        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role with name: USER not found!"));

        Cart cart = new Cart();
        cart.setCreatedBy(request.email());
        cart.setTotalAmount(BigDecimal.ZERO);

        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .cart(cart)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token: " + token + " not found!"));

        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new TokenExpiredException("Activation token: " + token + " has expired." +
                    " A new token has been sent.");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID: " + savedToken.getUser().getId() + " not found!"));

        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if(userRepository.findByEmail(request.email()).isEmpty()) {
            throw new ResourceNotFoundException("Email " + request.email() + " not found!");
        }

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User)auth.getPrincipal());
        claims.put("fullName", user.fullname());
        var jwtToken = jwtService.generateToken(claims, user);
        return new AuthenticationResponse(jwtToken);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.fullname(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
