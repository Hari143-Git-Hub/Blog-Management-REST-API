package com.example.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.blog.model.Post;
import com.example.blog.model.Category;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.dto.PostResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepo;
    private final CategoryRepository categoryRepo;

    public PostService(PostRepository postRepo, CategoryRepository categoryRepo) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
    }

    // ✅ UPDATED → returning DTO instead of entity
    public List<PostResponse> getAllPosts() {
        return postRepo.findAll().stream()
                .map(post -> new PostResponse(
                	    post.getId(),
                	    post.getTitle(),
                	    post.getContent(),
                	    post.getAuthor(),
                	    post.getCategory().getId(),
                	    post.getCategory().getName(),
                	    post.getCreatedAt(),
                	    post.getUpdatedAt()
                	))
                .toList();
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    public Post createPost(Post post, Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        post.setCategory(category);
        return postRepo.save(post);
    }

    public Post updatePost(Long id, Post newPost) {
        Post existing = getPostById(id);
        existing.setTitle(newPost.getTitle());
        existing.setContent(newPost.getContent());
        existing.setAuthor(newPost.getAuthor());
        return postRepo.save(existing);
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepo.delete(post);
    }

    public List<Post> getPostsByCategory(Long categoryId) {
        return postRepo.findByCategoryId(categoryId);
    }

    public List<Post> getPostsByAuthor(String author) {
        return postRepo.findByAuthor(author);
    }
 // ✅ Pagination + DTO
    public Page<PostResponse> getPostsPaged(Pageable pageable) {
        return postRepo.findAll(pageable)
                .map(post -> new PostResponse(
                	    post.getId(),
                	    post.getTitle(),
                	    post.getContent(),
                	    post.getAuthor(),
                	    post.getCategory().getId(),
                	    post.getCategory().getName(),
                	    post.getCreatedAt(),
                	    post.getUpdatedAt()
                	));
    }
}