package com.coffeshop.coffeeshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baristaName;
    private String coffeeCode;
    private int quantity;
    private double totalPrice; 
    private String date; 
}
