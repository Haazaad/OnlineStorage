package ru.haazad.onlinestorage.api.dtos;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String role;

    public UserDto() {}

    public UserDto(Long id) {
        this.id = id;
    }

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto(Long id, String username, String password, String passwordConfirm, String email, LocalDateTime createdDate, LocalDateTime updatedDate, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
