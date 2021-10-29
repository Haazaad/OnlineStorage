package ru.haazad.onlinestorage.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.haazad.onlinestorage.api.dtos.UserDto;

@Component
@RequiredArgsConstructor
public class UserServiceIntegration {
    private final WebClient userServiceWebClient;

    public UserDto getUserDto(String username) {
        return userServiceWebClient.get()
                .uri("/api/v1/users/" + username)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
