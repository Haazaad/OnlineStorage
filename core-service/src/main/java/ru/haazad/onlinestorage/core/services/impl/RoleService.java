package ru.haazad.onlinestorage.core.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.core.models.Role;
import ru.haazad.onlinestorage.core.repositories.RoleRepository;

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
