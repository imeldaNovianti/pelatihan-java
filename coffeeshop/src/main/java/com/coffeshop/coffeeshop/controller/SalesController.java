package com.coffeshop.coffeeshop.controller;

import com.coffeshop.coffeeshop.model.Sales;
import com.coffeshop.coffeeshop.model.Coffee;
import com.coffeshop.coffeeshop.model.Barista;
import com.coffeshop.coffeeshop.repository.SalesRepository;
import com.coffeshop.coffeeshop.repository.CoffeeRepository;
import com.coffeshop.coffeeshop.repository.BaristaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private BaristaRepository baristaRepository;

    // ✅ Endpoint untuk menampilkan semua data sales
    @GetMapping
    public Map<String, Object> getAllSales() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Berhasil mengambil semua data penjualan");
        response.put("data", salesRepository.findAll());
        return response;
    }

    // ✅ Endpoint untuk menambahkan data sales via JSON Body (nested object)
    @PostMapping
    public Map<String, Object> addSales(@RequestBody Sales sales) {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            // Ambil kode kopi dan ID barista dari nested object dalam body JSON
            String coffeeCode = sales.getCoffee().getCode();
            Long baristaId = sales.getBarista().getId();

            // Cari kopi dan barista dari database
            Optional<Coffee> coffeeOpt = coffeeRepository.findByCode(coffeeCode);
            Optional<Barista> baristaOpt = baristaRepository.findById(baristaId);

            // Validasi kopi
            if (coffeeOpt.isEmpty()) {
                response.put("message", "Kopi dengan kode " + coffeeCode + " tidak ditemukan");
                return response;
            }

            // Validasi barista
            if (baristaOpt.isEmpty()) {
                response.put("message", "Barista dengan ID " + baristaId + " tidak ditemukan");
                return response;
            }

            // Set data valid ke dalam objek sales
            sales.setCoffee(coffeeOpt.get());
            sales.setBarista(baristaOpt.get());

            // Simpan ke database
            Sales saved = salesRepository.save(sales);
            response.put("message", "Data penjualan berhasil disimpan");
            response.put("data", saved);

        } catch (Exception e) {
            response.put("message", "Terjadi kesalahan: " + e.getMessage());
            response.put("data", null);
        }

        return response;
    }

    // ✅ Endpoint untuk mengambil data penjualan berdasarkan ID
    @GetMapping("/{id}")
    public Map<String, Object> getSalesById(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isPresent()) {
            response.put("message", "Data penjualan ditemukan");
            response.put("data", sales.get());
        } else {
            response.put("message", "Penjualan dengan ID " + id + " tidak ditemukan");
            response.put("data", null);
        }

        return response;
    }

    // ✅ Endpoint untuk menghapus data penjualan berdasarkan ID
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteSales(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isPresent()) {
            salesRepository.delete(sales.get());
            response.put("message", "Data penjualan berhasil dihapus");
            response.put("data", sales.get());
        } else {
            response.put("message", "Data penjualan tidak ditemukan");
            response.put("data", null);
        }

        return response;
    }
}
