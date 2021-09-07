package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.haazad.onlinestorage.webapp.dtos.UserDto;
import ru.haazad.onlinestorage.webapp.models.Role;
import ru.haazad.onlinestorage.webapp.models.User;
import ru.haazad.onlinestorage.webapp.services.impl.RoleService;
import ru.haazad.onlinestorage.webapp.services.impl.UserService;

import javax.transaction.Transactional;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    @PostMapping("/users")
    public void createNewUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        Role role = roleService.findByRoleName(userDto.getRole());
        user.setRoles(Collections.singletonList(role));
        userService.addNewUser(user);
    }
}
