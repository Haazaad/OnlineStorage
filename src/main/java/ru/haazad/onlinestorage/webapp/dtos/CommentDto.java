package ru.haazad.onlinestorage.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.haazad.onlinestorage.webapp.models.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private Long productId;
    private String username;
    private LocalDateTime creationDate;
    private String description;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.productId = comment.getProductId();
        this.username = comment.getUser().getUsername();
        this.creationDate = comment.getCreationDate();
        this.description = comment.getDescription();
    }
}
