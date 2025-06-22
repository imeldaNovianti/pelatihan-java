package com.coffeshop.coffeeshop.controller;

import com.coffeshop.coffeeshop.model.Coffee;
import com.coffeshop.coffeeshop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/semua")
    public Map<String, Object> getAll() {
        List<Coffee> list = coffeeRepository.findAll();
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "Berhasil mengambil semua data kopi");
        res.put("data", list);
        return res;
    }

    @PostMapping("/nyimpan")
    public Map<String, Object> save(@RequestBody Coffee coffee) {
        Map<String, Object> res = new LinkedHashMap<>();
        Optional<Coffee> existing = coffeeRepository.findByCode(coffee.getCode());

        if (existing.isPresent()) {
            res.put("message", "Kode kopi sudah terdaftar");
        } else {
            Coffee saved = coffeeRepository.save(coffee);
            res.put("message", "Berhasil menyimpan kopi");
            res.put("data", saved);
        }
        return res;
    }

    @GetMapping("/{code}")
    public Map<String, Object> getByCode(@PathVariable String code) {
        Optional<Coffee> coffee = coffeeRepository.findByCode(code);
        Map<String, Object> res = new LinkedHashMap<>();
        if (coffee.isPresent()) {
            res.put("message", "Kopi ditemukan");
            res.put("data", coffee.get());
        } else {
            res.put("message", "Kopi dengan kode " + code + " tidak ditemukan");
            res.put("data", null);
        }
        return res;
    }

    @PutMapping("/{code}")
    public Map<String, Object> update(@PathVariable String code, @RequestBody Coffee newData) {
        Optional<Coffee> existing = coffeeRepository.findByCode(code);
        Map<String, Object> res = new LinkedHashMap<>();
        if (existing.isPresent()) {
            Coffee coffee = existing.get();
            coffee.setMerk(newData.getMerk());
            coffee.setType(newData.getType());
            coffee.setPrice(newData.getPrice());
            Coffee updated = coffeeRepository.save(coffee);
            res.put("message", "Data kopi berhasil diperbarui");
            res.put("data", updated);
        } else {
            res.put("message", "Kopi tidak ditemukan");
        }
        return res;
    }

    @DeleteMapping("/{code}")
    public Map<String, Object> delete(@PathVariable String code) {
        Optional<Coffee> coffee = coffeeRepository.findByCode(code);
        Map<String, Object> res = new LinkedHashMap<>();
        if (coffee.isPresent()) {
            coffeeRepository.delete(coffee.get());
            res.put("message", "Kopi berhasil dihapus");
            res.put("data", coffee.get());
        } else {
            res.put("message", "Kopi tidak ditemukan");
        }
        return res;
    }
}
