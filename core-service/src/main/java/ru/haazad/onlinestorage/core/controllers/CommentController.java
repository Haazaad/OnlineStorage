package ru.haazad.onlinestorage.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.dtos.CommentDto;
import ru.haazad.onlinestorage.core.services.impl.CommentService;
import ru.haazad.onlinestorage.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;
    private final Converter converter;

    @GetMapping
    public List<CommentDto> findAllCommentByProductId(@RequestParam(required = true) Long productId) {
        return commentService.findAllCommentByProduct(productId).stream().map(converter::commentToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void addNewComment(@RequestHeader String username, @RequestBody CommentDto commentDto) {
        commentService.createNewComment(username, commentDto);
    }
}
