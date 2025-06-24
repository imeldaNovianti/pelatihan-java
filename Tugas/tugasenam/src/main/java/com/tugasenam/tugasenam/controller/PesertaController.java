package com.tugasenam.tugasenam.controller;

import com.tugasenam.tugasenam.model.*;
import com.tugasenam.tugasenam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/peserta")
public class PesertaController {

    @Autowired
    private PesertaService pesertaService;

    @Autowired
    private KursusService kursusService;

    @PostMapping
    public Map<String, Object> tambahPeserta(@RequestBody Peserta peserta) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Peserta berhasil ditambahkan");
        response.put("data", pesertaService.tambahPeserta(peserta));
        return response;
    }

    @GetMapping
    public Map<String, Object> getAllPeserta() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", pesertaService.getAllPeserta());
        return response;
    }

    @PostMapping("/{id}/daftar/{kodeKursus}")
    public Map<String, Object> daftarKursus(@PathVariable String id, @PathVariable String kodeKursus) {
        Map<String, Object> response = new HashMap<>();
        Kursus kursus = kursusService.getByKode(kodeKursus);
        if (kursus == null) {
            response.put("message", "Kursus tidak ditemukan");
            return response;
        }
        String result = pesertaService.daftarKursus(id, kursus);
        response.put("message", result);
        return response;
    }
}
