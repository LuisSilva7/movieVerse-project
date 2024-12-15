package org.movieverse.movieverse_backend.config.data;

import lombok.RequiredArgsConstructor;
import org.movieverse.movieverse_backend.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Configuration
public class RolesStarter implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    @Transactional
    public void run(String... args) {
        roleService.rolesStarter();
    }
}