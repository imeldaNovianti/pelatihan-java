package com.tugasenam.tugasenam.service;

import com.tugasenam.tugasenam.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class KursusService {
    private final List<Kursus> kursusList = new ArrayList<>();

    public Kursus tambahKursus(Kursus kursus, Instruktur instruktur) {
        kursus.setInstruktur(instruktur);
        kursusList.add(kursus);
        return kursus;
    }

    public List<Kursus> getAllKursus() {
        return kursusList;
    }

    public Kursus getByKode(String kode) {
        return kursusList.stream()
                .filter(k -> k.getKode().equals(kode))
                .findFirst()
                .orElse(null);
    }
}