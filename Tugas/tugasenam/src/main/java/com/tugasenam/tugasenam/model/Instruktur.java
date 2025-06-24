package com.tugasenam.tugasenam.model;
import lombok.Data;
import java.util.UUID;

@Data
public class Instruktur {
    private String id;
    private String nama;
    private String keahlian;

    public Instruktur() {
        this.id = UUID.randomUUID().toString();
    }
}