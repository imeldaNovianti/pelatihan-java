package com.tugasenam.tugasenam.service;

import com.tugasenam.tugasenam.model.Instruktur;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InstrukturService {
    private final List<Instruktur> instrukturList = new ArrayList<>();

    public Instruktur tambahInstruktur(Instruktur instruktur) {
        instrukturList.add(instruktur);
        return instruktur;
    }

    public List<Instruktur> getAllInstruktur() {
        return instrukturList;
    }

    public Instruktur getById(String id) {
        return instrukturList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
