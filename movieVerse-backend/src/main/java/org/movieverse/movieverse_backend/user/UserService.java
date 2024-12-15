package org.movieverse.movieverse_backend.user;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.cart.Cart;
import org.movieverse.movieverse_backend.exception.custom.ResourceNotFoundException;
import org.movieverse.movieverse_backend.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found!"));
    }

    public void adminUserStarter() {
        Optional<User> userAdmin = userRepository.findByEmail("silva@gmail.com");

        var userRole = roleService.findByName("ADMIN")
                .orElseThrow(() -> new ResourceNotFoundException("Role with name: ADMIN not found!"));

        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin already exists!"),
                () -> {
                    User user = User.builder()
                            .firstname("Luís")
                            .lastname("Silva")
                            .email("silva@gmail.com")
                            .password(passwordEncoder.encode("12345"))
                            .accountLocked(false)
                            .enabled(true)
                            .roles(List.of(userRole))
                            .cart(new Cart())
                            .build();

                    userRepository.save(user);
                }
        );
    }
}
