package com.perusahaan.demo.controller;

import com.perusahaan.demo.model.Proyek;
import com.perusahaan.demo.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @GetMapping
    public List<Proyek> getAll() {
        return proyekService.getAll();
    }

    @PostMapping
    public String add(@RequestBody Proyek proyek) {
        proyekService.add(proyek);
        return "Proyek berhasil ditambahkan.";
    }
}
