package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.haazad.onlinestorage.webapp.dtos.UserDto;
import ru.haazad.onlinestorage.webapp.exceptions.ApplicationError;
import ru.haazad.onlinestorage.webapp.services.impl.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect password confirmation."), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.addNewUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
