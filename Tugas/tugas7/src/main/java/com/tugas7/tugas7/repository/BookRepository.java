package com.tugas7.tugas7.repository;

import com.tugas7.tugas7.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
