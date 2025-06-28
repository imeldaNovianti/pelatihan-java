package com.tugas7.tugas7.controller;

import com.tugas7.tugas7.model.Book;
import com.tugas7.tugas7.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBook(@RequestBody Book book) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", bookRepository.save(book));
        res.put("message", "Book berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", bookRepository.findAll());
        res.put("message", "Semua data book berhasil diambil");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            res.put("data", book.get());
            res.put("message", "Book ditemukan");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Book tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long id, @RequestBody Book newBook) {
        Map<String, Object> res = new HashMap<>();
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book book = bookData.get();
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setCategory(newBook.getCategory());
            res.put("data", bookRepository.save(book));
            res.put("message", "Book berhasil diperbarui");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Book tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            res.put("message", "Book berhasil dihapus");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Book tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

}
