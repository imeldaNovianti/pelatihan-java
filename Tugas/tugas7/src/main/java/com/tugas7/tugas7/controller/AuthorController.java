package com.tugas7.tugas7.controller;

import com.tugas7.tugas7.model.Author;
import com.tugas7.tugas7.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setName(updatedAuthor.getName());
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}