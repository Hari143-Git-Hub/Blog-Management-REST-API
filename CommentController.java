package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.service.CommentService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getByPost(@PathVariable Long postId) {
        return service.getCommentsByPost(postId);
    }

    @PostMapping("/post/{postId}")
    public Comment add(@PathVariable Long postId, @RequestBody Comment comment) {
        return service.addComment(postId, comment);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteComment(id);
        return "Comment deleted";
    }
}