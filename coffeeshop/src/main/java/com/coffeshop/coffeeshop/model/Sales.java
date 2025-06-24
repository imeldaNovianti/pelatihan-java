package com.coffeshop.coffeeshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID penjualan (primary key)

    private String date; // Tanggal penjualan

    // Relasi Many-to-One dengan Coffee (kode kopi sebagai FK)
    @ManyToOne
    @JoinColumn(name = "code_coffee", referencedColumnName = "code")
    private Coffee coffee;

    // Relasi Many-to-One dengan Barista (ID barista sebagai FK)
    @ManyToOne
    @JoinColumn(name = "id_barista", referencedColumnName = "id")
    private Barista barista;
}
