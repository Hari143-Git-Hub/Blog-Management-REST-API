package com.example.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content is required")
    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Long getId() { return id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }
}