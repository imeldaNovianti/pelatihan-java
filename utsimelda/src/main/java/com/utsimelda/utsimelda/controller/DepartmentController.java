package com.utsimelda.utsimelda.controller;

import com.utsimelda.utsimelda.model.Department;
import com.utsimelda.utsimelda.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository deptRepo;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return buildResponse("Daftar departemen", deptRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return deptRepo.findById(id)
                .map(d -> buildResponse("Detail departemen", d, HttpStatus.OK))
                .orElse(buildResponse("Departemen tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Department dept) {
        return buildResponse("Departemen berhasil dibuat", deptRepo.save(dept), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Department input) {
        return deptRepo.findById(id)
                .map(existing -> {
                    existing.setName(input.getName());
                    existing.setCompany(input.getCompany());
                    return buildResponse("Departemen berhasil diperbarui",
                            deptRepo.save(existing), HttpStatus.OK);
                })
                .orElse(buildResponse("Departemen tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return deptRepo.findById(id)
                .map(existing -> {
                    deptRepo.delete(existing);
                    return buildResponse("Departemen berhasil dihapus", null, HttpStatus.OK);
                })
                .orElse(buildResponse("Departemen tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchDepartments(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long companyId,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort sortOrder = sort.equalsIgnoreCase("desc") ?
                Sort.by("name").descending() : Sort.by("name").ascending();

        List<Department> list = deptRepo.findAll(sortOrder);

        if (keyword != null) {
            list = list.stream()
                    .filter(d -> d.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        }

        if (companyId != null) {
            list = list.stream()
                    .filter(d -> d.getCompany() != null && d.getCompany().getId().equals(companyId))
                    .toList();
        }

        return buildResponse("Hasil pencarian departemen", list, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortByName(@RequestParam(defaultValue = "asc") String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by("name").descending() : Sort.by("name").ascending();

        return buildResponse("Departemen diurutkan berdasarkan nama (" + order.toUpperCase() + ")", deptRepo.findAll(sort), HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> buildResponse(String msg, Object data, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", msg);
        body.put("data", data);
        return new ResponseEntity<>(body, status);
    }
}
