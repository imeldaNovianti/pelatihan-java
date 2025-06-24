package com.tugasenam.tugasenam.controller;

import com.tugasenam.tugasenam.model.*;
import com.tugasenam.tugasenam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/kursus")
public class KursusController {

    @Autowired
    private KursusService kursusService;

    @Autowired
    private InstrukturService instrukturService;

    @PostMapping
    public Map<String, Object> tambahKursus(@RequestParam String instrukturId, @RequestBody Kursus kursus) {
        Map<String, Object> response = new HashMap<>();
        Instruktur instruktur = instrukturService.getById(instrukturId);

        if (instruktur == null) {
            response.put("message", "Instruktur tidak ditemukan");
            return response;
        }

        Kursus result = kursusService.tambahKursus(kursus, instruktur);
        response.put("message", "Kursus berhasil ditambahkan");
        response.put("data", result);
        return response;
    }

    @GetMapping
    public Map<String, Object> getAllKursus() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", kursusService.getAllKursus());
        return response;
    }

    @GetMapping("/{kode}")
    public Map<String, Object> getDetailKursus(@PathVariable String kode) {
        Map<String, Object> response = new HashMap<>();
        Kursus kursus = kursusService.getByKode(kode);

        if (kursus == null) {
            response.put("message", "Kursus tidak ditemukan");
            return response;
        }

        response.put("data", kursus);
        return response;
    }
}
