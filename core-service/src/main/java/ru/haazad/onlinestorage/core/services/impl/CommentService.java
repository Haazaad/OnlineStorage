package ru.haazad.onlinestorage.core.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.api.dtos.CommentDto;
import ru.haazad.onlinestorage.core.models.Comment;
import ru.haazad.onlinestorage.core.models.User;
import ru.haazad.onlinestorage.core.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    public List<Comment> findAllCommentByProduct(Long productId) {
        return commentRepository.findAllByProductId(productId);
    }

    public void createNewComment(String username, CommentDto commentDto) {
        User user = userService.findByUsername(username);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setProductId(commentDto.getProductId());
        comment.setDescription(commentDto.getDescription());
        commentRepository.save(comment);
    }
}
