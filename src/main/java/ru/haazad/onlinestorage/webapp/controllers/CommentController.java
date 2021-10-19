package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dtos.CommentDto;
import ru.haazad.onlinestorage.webapp.services.impl.CommentService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> findAllCommentByProductId(@RequestParam(required = true) Long productId) {
        return commentService.findAllCommentByProduct(productId);
    }

    @PostMapping
    public void addNewComment(Principal principal, @RequestBody CommentDto commentDto) {
        commentService.createNewComment(principal.getName(), commentDto);
    }
}
