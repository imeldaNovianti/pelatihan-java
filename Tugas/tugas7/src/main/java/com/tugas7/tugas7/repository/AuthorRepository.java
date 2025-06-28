package com.tugas7.tugas7.repository;

import com.tugas7.tugas7.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}