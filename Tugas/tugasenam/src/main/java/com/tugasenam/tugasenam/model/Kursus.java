package com.tugasenam.tugasenam.model;

import lombok.Data;
import java.util.*;

@Data
public class Kursus {
    private String kode;
    private String namaKursus;
    private int kuota;
    private Instruktur instruktur;
    private List<Peserta> pesertaList;

    public Kursus() {
        this.kode = UUID.randomUUID().toString().substring(0, 8);
        this.pesertaList = new ArrayList<>();
    }
}