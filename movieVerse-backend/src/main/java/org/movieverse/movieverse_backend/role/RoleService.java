package org.movieverse.movieverse_backend.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void rolesStarter() {
        Optional<Role> adminRole = findByName("ADMIN");

        adminRole.ifPresentOrElse(
                role -> System.out.println("Role already exists!"),
                () -> {
                    Role role = new Role();
                    role.setName("ADMIN");

                    roleRepository.save(role);

                    Role role2 = new Role();
                    role.setName("USER");

                    roleRepository.save(role2);
                }
        );
    }
}
