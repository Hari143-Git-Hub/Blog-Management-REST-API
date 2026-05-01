package com.example.blog.service;

import com.example.blog.model.Category;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public Category createCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Long id, Category newCategory) {
        Category existing = getCategoryById(id);
        existing.setName(newCategory.getName());
        existing.setDescription(newCategory.getDescription());
        return repository.save(existing);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        repository.delete(category);
    }
}