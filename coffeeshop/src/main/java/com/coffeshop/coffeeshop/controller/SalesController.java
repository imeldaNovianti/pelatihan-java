package com.coffeshop.coffeeshop.controller;

import com.coffeshop.coffeeshop.model.Sales;
import com.coffeshop.coffeeshop.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesRepository salesRepository;

    @GetMapping("/semua")
    public Map<String, Object> getAll() {
        List<Sales> list = salesRepository.findAll();
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "Berhasil mengambil semua data penjualan");
        res.put("data", list);
        return res;
    }

    @PostMapping("/nyimpan")
    public Map<String, Object> save(@RequestBody Sales sales) {
        Sales saved = salesRepository.save(sales);
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "Berhasil menyimpan data penjualan");
        res.put("data", saved);
        return res;
    }

    @GetMapping("/barista/{baristaName}")
    public Map<String, Object> getByBarista(@PathVariable String baristaName) {
        List<Sales> list = salesRepository.findByBaristaName(baristaName);
        Map<String, Object> res = new LinkedHashMap<>();
        if (list.isEmpty()) {
            res.put("message", "Penjualan oleh " + baristaName + " tidak ditemukan");
        } else {
            res.put("message", "Penjualan oleh " + baristaName + " berhasil ditemukan");
            res.put("data", list);
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Optional<Sales> sales = salesRepository.findById(id);
        Map<String, Object> res = new LinkedHashMap<>();
        if (sales.isPresent()) {
            salesRepository.deleteById(id);
            res.put("message", "Data penjualan berhasil dihapus");
            res.put("data", sales.get());
        } else {
            res.put("message", "Data penjualan tidak ditemukan");
        }
        return res;
    }
}
