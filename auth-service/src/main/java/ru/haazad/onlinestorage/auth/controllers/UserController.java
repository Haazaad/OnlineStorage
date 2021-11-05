package ru.haazad.onlinestorage.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.dtos.UserDto;
import ru.haazad.onlinestorage.api.exceptions.ApplicationError;
import ru.haazad.onlinestorage.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{username}")
    public UserDto getUser(@PathVariable String username) {
        return new UserDto(userService.findByUsername(username).getId());
    }

    @PostMapping("/users")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            return new ResponseEntity<>(new ApplicationError(HttpStatus.UNPROCESSABLE_ENTITY.toString(), "Incorrect password confirmation."), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.addNewUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me/header")
    public String aboutUser(@RequestHeader(required = false) String username) {
        return username != null ? username : "empty";
    }
}
