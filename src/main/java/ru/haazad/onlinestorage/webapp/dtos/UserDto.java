package ru.haazad.onlinestorage.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String role;
}
