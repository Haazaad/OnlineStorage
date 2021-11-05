package ru.haazad.onlinestorage.auth.utils;

import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.api.dtos.UserDto;
import ru.haazad.onlinestorage.auth.models.User;

@Component
public class Converter {
    public UserDto userToDto(User user) {
        return new UserDto(user.getId());
    }
}
