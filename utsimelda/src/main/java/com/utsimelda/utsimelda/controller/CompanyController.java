package com.utsimelda.utsimelda.controller;

import com.utsimelda.utsimelda.model.Company;
import com.utsimelda.utsimelda.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepo;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return buildResponse("Daftar perusahaan", companyRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return companyRepo.findById(id)
                .map(c -> buildResponse("Detail perusahaan", c, HttpStatus.OK))
                .orElse(buildResponse("Perusahaan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Company company) {
        return buildResponse("Perusahaan berhasil dibuat", companyRepo.save(company), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Company input) {
        return companyRepo.findById(id)
                .map(existing -> {
                    existing.setName(input.getName());
                    existing.setAddress(input.getAddress());
                    return buildResponse("Perusahaan berhasil diperbarui",
                            companyRepo.save(existing), HttpStatus.OK);
                })
                .orElse(buildResponse("Perusahaan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return companyRepo.findById(id)
                .map(existing -> {
                    companyRepo.delete(existing);
                    return buildResponse("Perusahaan berhasil dihapus", null, HttpStatus.OK);
                })
                .orElse(buildResponse("Perusahaan tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort order = sort.equalsIgnoreCase("desc")
                ? Sort.by("name").descending()
                : Sort.by("name").ascending();

        List<Company> list = companyRepo.findAll(order);

        if (keyword != null) {
            list = list.stream()
                    .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase())
                              || (c.getAddress() != null && c.getAddress().toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
        }

        return buildResponse("Hasil pencarian perusahaan", list, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortByName(@RequestParam(defaultValue = "asc") String order) {
        Sort sortOrder = order.equalsIgnoreCase("desc")
                ? Sort.by("name").descending()
                : Sort.by("name").ascending();

        return buildResponse("Perusahaan diurutkan berdasarkan nama (" + order.toUpperCase() + ")",
                companyRepo.findAll(sortOrder), HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> buildResponse(String msg, Object data, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", msg);
        body.put("data", data);
        return new ResponseEntity<>(body, status);
    }
}
