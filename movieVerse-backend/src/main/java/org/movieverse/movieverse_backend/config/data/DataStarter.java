package org.movieverse.movieverse_backend.config.data;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.movie.MovieService;
import org.movieverse.movieverse_backend.role.RoleService;
import org.movieverse.movieverse_backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DataStarter implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final MovieService movieService;

    @Override
    public void run(String... args) {
        roleService.rolesStarter();
        userService.adminUserStarter();
        movieService.moviesStarter();
    }
}