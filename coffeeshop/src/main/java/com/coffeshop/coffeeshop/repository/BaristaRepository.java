package com.coffeshop.coffeeshop.repository;

import com.coffeshop.coffeeshop.model.Barista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaristaRepository extends JpaRepository<Barista, Long> {

    List<Barista> findBaristaByEmail(String email);

    Optional<Barista> findByEmail(String email);
}
