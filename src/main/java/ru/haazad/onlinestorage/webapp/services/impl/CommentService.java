package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.dtos.CommentDto;
import ru.haazad.onlinestorage.webapp.models.Comment;
import ru.haazad.onlinestorage.webapp.models.User;
import ru.haazad.onlinestorage.webapp.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    public List<CommentDto> findAllCommentByProduct(Long productId) {
        return commentRepository.findAllByProductId(productId).stream().map(CommentDto::new).collect(Collectors.toList());
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
