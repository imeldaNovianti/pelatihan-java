package com.kuis11.kuis11.controller;

import com.kuis11.kuis11.model.Department;
import com.kuis11.kuis11.repository.DepartmentRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository repo;

    public DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Department d) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", repo.save(d));
        res.put("message", "Department berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", repo.findAll());
        res.put("message", "Semua department berhasil diambil");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Optional<Department> data = repo.findById(id);
        Map<String, Object> res = new HashMap<>();
        if (data.isPresent()) {
            res.put("data", data.get());
            res.put("message", "Department ditemukan");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Department tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Department r) {
        Map<String, Object> res = new HashMap<>();
        Optional<Department> data = repo.findById(id);
        if (data.isPresent()) {
            Department d = data.get();
            d.setName(r.getName());
            res.put("data", repo.save(d));
            res.put("message", "Department berhasil diperbarui");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Department tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        if (repo.existsById(id)) {
            repo.deleteById(id);
            res.put("message", "Department berhasil dihapus");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Department tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
}
