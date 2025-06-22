package com.coffeshop.coffeeshop.repository;

import com.coffeshop.coffeeshop.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    List<Sales> findByBaristaName(String baristaName);

    List<Sales> findByCoffeeCode(String coffeeCode);
}
