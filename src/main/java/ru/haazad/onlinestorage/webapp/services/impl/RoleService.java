package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.models.Role;
import ru.haazad.onlinestorage.webapp.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new ResourceNotFoundException(String.format("Not found role %s", roleName)));
    }
}
