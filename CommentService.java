package com.example.blog.service;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    public CommentService(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        comment.setPost(post);
        return commentRepo.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        commentRepo.delete(comment);
    }
}