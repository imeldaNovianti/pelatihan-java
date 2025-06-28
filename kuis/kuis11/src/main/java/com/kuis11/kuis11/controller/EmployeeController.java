package com.kuis11.kuis11.controller;

import com.kuis11.kuis11.model.Employee;
import com.kuis11.kuis11.repository.EmployeeRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Employee e) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", repo.save(e));
        res.put("message", "Employee berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", repo.findAll());
        res.put("message", "Semua employee berhasil diambil");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Optional<Employee> data = repo.findById(id);
        Map<String, Object> res = new HashMap<>();
        if (data.isPresent()) {
            res.put("data", data.get());
            res.put("message", "Employee ditemukan");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Employee tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Employee r) {
        Map<String, Object> res = new HashMap<>();
        Optional<Employee> data = repo.findById(id);
        if (data.isPresent()) {
            Employee e = data.get();
            e.setName(r.getName());
            res.put("data", repo.save(e));
            res.put("message", "Employee berhasil diperbarui");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Employee tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        if (repo.existsById(id)) {
            repo.deleteById(id);
            res.put("message", "Employee berhasil dihapus");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Employee tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
}
