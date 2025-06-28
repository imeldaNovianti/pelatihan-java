package com.tugas7.tugas7.controller;

import com.tugas7.tugas7.model.Category;
import com.tugas7.tugas7.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}