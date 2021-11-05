package ru.haazad.onlinestorage.api.dtos;

import java.time.LocalDateTime;

public class CommentDto {

    private Long id;
    private Long productId;
    private String username;
    private LocalDateTime creationDate;
    private String description;

    public CommentDto() {
    }

    public CommentDto(Long id, Long productId, String username, LocalDateTime creationDate, String description) {
        this.id = id;
        this.productId = productId;
        this.username = username;
        this.creationDate = creationDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
