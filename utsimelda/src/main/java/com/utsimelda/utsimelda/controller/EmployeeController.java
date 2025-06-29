package com.utsimelda.utsimelda.controller;

import com.utsimelda.utsimelda.model.Employee;
import com.utsimelda.utsimelda.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository empRepo;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return buildResponse("Daftar semua karyawan", empRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return empRepo.findById(id)
                .map(emp -> buildResponse("Data karyawan ditemukan", emp, HttpStatus.OK))
                .orElse(buildResponse("Karyawan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Employee emp) {
        return buildResponse("Karyawan berhasil dibuat", empRepo.save(emp), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Employee input) {
        return empRepo.findById(id)
                .map(existing -> {
                    existing.setFullName(input.getFullName());
                    existing.setRole(input.getRole());
                    existing.setDepartment(input.getDepartment());
                    return buildResponse("Karyawan berhasil diperbarui", empRepo.save(existing), HttpStatus.OK);
                })
                .orElse(buildResponse("Karyawan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return empRepo.findById(id)
                .map(existing -> {
                    empRepo.delete(existing);
                    return buildResponse("Karyawan berhasil dihapus", null, HttpStatus.OK);
                })
                .orElse(buildResponse("Karyawan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort sortOrder = sort.equalsIgnoreCase("desc") ?
                Sort.by("fullName").descending() : Sort.by("fullName").ascending();

        List<Employee> results = empRepo.findAll(sortOrder);

        if (name != null) {
            results = results.stream()
                    .filter(e -> e.getFullName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (role != null) {
            results = results.stream()
                    .filter(e -> e.getRole().equalsIgnoreCase(role))
                    .toList();
        }

        return buildResponse("dari pencarian karyawan", results, HttpStatus.OK);
    }


    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortByName(
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by("fullName").descending() : Sort.by("fullName").ascending();

        return buildResponse("Data diurutkan berdasarkan nama (" + order.toUpperCase() + ")", empRepo.findAll(sort), HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> buildResponse(String msg, Object data, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", msg);
        body.put("data", data);
        return new ResponseEntity<>(body, status);
    }
}
