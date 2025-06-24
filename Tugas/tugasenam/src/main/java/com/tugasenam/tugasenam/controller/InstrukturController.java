package com.tugasenam.tugasenam.controller;

import com.tugasenam.tugasenam.model.Instruktur;
import com.tugasenam.tugasenam.service.InstrukturService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/instruktur")
public class InstrukturController {

    @Autowired
    private InstrukturService instrukturService;

    @PostMapping
    public Map<String, Object> tambahInstruktur(@RequestBody Instruktur instruktur) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Instruktur berhasil ditambahkan");
        response.put("data", instrukturService.tambahInstruktur(instruktur));
        return response;
    }

    @GetMapping
    public Map<String, Object> getAllInstruktur() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", instrukturService.getAllInstruktur());
        return response;
    }
}