package org.movieverse.movieverse_backend.config.data;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
public class AdminUserStarter implements CommandLineRunner {

    private final UserService userService;

    @Override
    @Transactional
    public void run(String... args) {
        userService.adminUserStarter();
    }
}
