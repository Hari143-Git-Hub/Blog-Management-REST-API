package com.example.blog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import com.example.blog.dto.PostResponse;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
 // ✅ Pagination endpoint
    @GetMapping("/paged")
    public Page<PostResponse> getPagedPosts(Pageable pageable) {
        return service.getPostsPaged(pageable);
    }

    // ✅ UPDATED → returns DTO
    @GetMapping
    public List<PostResponse> getAll() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return service.getPostById(id);
    }

    @PostMapping("/category/{categoryId}")
    public Post create(@PathVariable Long categoryId, @RequestBody Post post) {
        return service.createPost(post, categoryId);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        return service.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deletePost(id);
        return "Post deleted";
    }

    @GetMapping("/category/{categoryId}")
    public List<Post> getByCategory(@PathVariable Long categoryId) {
        return service.getPostsByCategory(categoryId);
    }

    @GetMapping("/author/{author}")
    public List<Post> getByAuthor(@PathVariable String author) {
        return service.getPostsByAuthor(author);
    }
}