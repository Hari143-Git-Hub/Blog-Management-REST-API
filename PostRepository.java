package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 👉 ADD THESE
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);

    List<Post> findByAuthor(String author);

    // 👉 ADD THIS (pagination)
    Page<Post> findAll(Pageable pageable);
}