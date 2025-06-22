package com.coffeshop.coffeeshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Barista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String email;
}
