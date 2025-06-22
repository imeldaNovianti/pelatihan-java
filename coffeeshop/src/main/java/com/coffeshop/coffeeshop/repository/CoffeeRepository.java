package com.coffeshop.coffeeshop.repository;

import com.coffeshop.coffeeshop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, String> {

    List<Coffee> findCoffeeByMerk(String merk);

    Optional<Coffee> findByCode(String code);
}
