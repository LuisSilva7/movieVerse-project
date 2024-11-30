package org.movieverse.movieverse_backend.config;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.model.Cart;
import org.movieverse.movieverse_backend.model.User;
import org.movieverse.movieverse_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Optional<User> userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin already exists!"),
                () -> {
                    User user = new User();
                    user.setUsername("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));

                    Cart cart = new Cart();
                    user.setCart(cart);

                    userRepository.save(user);
                }
        );
    }
}
