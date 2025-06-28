package com.tugas7.tugas7.controller;

import com.tugas7.tugas7.model.Book;
import com.tugas7.tugas7.model.Author;
import com.tugas7.tugas7.model.Category;
import com.tugas7.tugas7.repository.BookRepository;
import com.tugas7.tugas7.repository.AuthorRepository;
import com.tugas7.tugas7.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(updatedBook.getName());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
