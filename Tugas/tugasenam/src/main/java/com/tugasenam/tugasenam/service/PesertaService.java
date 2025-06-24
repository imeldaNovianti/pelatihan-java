package com.tugasenam.tugasenam.service;

import com.tugasenam.tugasenam.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PesertaService {
    private final List<Peserta> pesertaList = new ArrayList<>();

    public Peserta tambahPeserta(Peserta peserta) {
        pesertaList.add(peserta);
        return peserta;
    }

    public List<Peserta> getAllPeserta() {
        return pesertaList;
    }

    public Peserta getById(String id) {
        return pesertaList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String daftarKursus(String pesertaId, Kursus kursus) {
        Peserta peserta = getById(pesertaId);
        if (peserta == null) return "Peserta tidak ditemukan";
        if (kursus.getPesertaList().size() >= kursus.getKuota()) return "Kuota kursus sudah penuh";

        kursus.getPesertaList().add(peserta);
        peserta.getKursusDiikuti().add(kursus);
        return "Berhasil daftar kursus";
    }
}