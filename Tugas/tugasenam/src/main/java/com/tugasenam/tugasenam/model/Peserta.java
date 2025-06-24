package com.tugasenam.tugasenam.model;

import lombok.Data;
import java.util.*;

@Data
public class Peserta {
    private String id;
    private String nama;
    private String email;
    private List<Kursus> kursusDiikuti;

    public Peserta() {
        this.id = UUID.randomUUID().toString();
        this.kursusDiikuti = new ArrayList<>();
    }
}