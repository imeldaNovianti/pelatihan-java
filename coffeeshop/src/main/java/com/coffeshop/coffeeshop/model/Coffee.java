package com.coffeshop.coffeeshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Coffee {

    @Id
    private String code; // PK manual
    private String merk;
    private String type;
    private double price;
}
