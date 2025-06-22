package com.coffeshop.coffeeshop.controller;

import com.coffeshop.coffeeshop.model.Barista;
import com.coffeshop.coffeeshop.repository.BaristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/barista")
public class BaristaController {

    @Autowired
    BaristaRepository baristaRepository;

    @GetMapping("/semua")
    public Map<String, Object> getAll() {
        List<Barista> list = baristaRepository.findAll();
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "Berhasil mengambil semua data barista");
        res.put("data", list);
        return res;
    }

    @PostMapping("/nyimpan")
    public Map<String, Object> save(@RequestBody Barista barista) {
        Map<String, Object> res = new LinkedHashMap<>();
        Optional<Barista> existing = baristaRepository.findByEmail(barista.getEmail());

        if (existing.isPresent()) {
            res.put("message", "Email sudah terdaftar");
        } else {
            Barista saved = baristaRepository.save(barista);
            res.put("message", "Berhasil menyimpan barista");
            res.put("data", saved);
        }
        return res;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        Optional<Barista> barista = baristaRepository.findById(id);
        Map<String, Object> res = new LinkedHashMap<>();
        if (barista.isPresent()) {
            res.put("message", "Barista ditemukan");
            res.put("data", barista.get());
        } else {
            res.put("message", "Barista tidak ditemukan");
        }
        return res;
    }

    @PutMapping("/{email}")
    public Map<String, Object> update(@PathVariable String email, @RequestBody Barista newData) {
        List<Barista> list = baristaRepository.findBaristaByEmail(email);
        Map<String, Object> res = new LinkedHashMap<>();
        if (!list.isEmpty()) {
            Barista existing = list.get(0);
            existing.setName(newData.getName());
            existing.setAge(newData.getAge());
            existing.setGender(newData.getGender());
            Barista updated = baristaRepository.save(existing);
            res.put("message", "Data barista berhasil diperbarui");
            res.put("data", updated);
        } else {
            res.put("message", "Barista tidak ditemukan");
        }
        return res;
    }

    @DeleteMapping("/{email}")
    public Map<String, Object> delete(@PathVariable String email) {
        List<Barista> list = baristaRepository.findBaristaByEmail(email);
        Map<String, Object> res = new LinkedHashMap<>();
        if (!list.isEmpty()) {
            Barista toDelete = list.get(0);
            baristaRepository.delete(toDelete);
            res.put("message", "Barista berhasil dihapus");
            res.put("data", toDelete);
        } else {
            res.put("message", "Barista tidak ditemukan");
        }
        return res;
    }
}
