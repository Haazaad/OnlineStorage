package ru.haazad.onlinestorage.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.auth.models.Role;
import ru.haazad.onlinestorage.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private static final String DEFAULT_USER_ROLE = "ROLE_USER";

    private final RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        if (roleName == null) {
            return roleRepository.findByName(DEFAULT_USER_ROLE).get();
        }
        return roleRepository.findByName(roleName).orElseThrow(() -> new ResourceNotFoundException(String.format("Not found role %s", roleName)));
    }
}
