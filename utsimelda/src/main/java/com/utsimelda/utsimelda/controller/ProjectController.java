package com.utsimelda.utsimelda.controller;

import com.utsimelda.utsimelda.model.Project;
import com.utsimelda.utsimelda.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projRepo;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return buildResponse("Daftar proyek", projRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return projRepo.findById(id)
                .map(p -> buildResponse("Detail proyek", p, HttpStatus.OK))
                .orElse(buildResponse("Proyek tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Project proj) {
        return buildResponse("Proyek berhasil dibuat", projRepo.save(proj), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Project input) {
        return projRepo.findById(id)
                .map(existing -> {
                    existing.setTitle(input.getTitle());
                    existing.setDescription(input.getDescription());
                    existing.setDepartment(input.getDepartment());
                    return buildResponse("Proyek berhasil diperbarui",
                            projRepo.save(existing), HttpStatus.OK);
                })
                .orElse(buildResponse("Proyek tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return projRepo.findById(id)
                .map(existing -> {
                    projRepo.delete(existing);
                    return buildResponse("Proyek berhasil dihapus", null, HttpStatus.OK);
                })
                .orElse(buildResponse("Proyek tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort sortOrder = sort.equalsIgnoreCase("desc") ?
                Sort.by("title").descending() : Sort.by("title").ascending();

        List<Project> list = projRepo.findAll(sortOrder);

        if (keyword != null) {
            list = list.stream()
                    .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase())
                              || (p.getDescription() != null && p.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                    .toList();
        }

        if (departmentId != null) {
            list = list.stream()
                    .filter(p -> p.getDepartment() != null && p.getDepartment().getId().equals(departmentId))
                    .toList();
        }

        return buildResponse("Hasil pencarian proyek", list, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortByTitle(@RequestParam(defaultValue = "asc") String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by("title").descending() : Sort.by("title").ascending();

        return buildResponse("Proyek diurutkan berdasarkan judul (" + order.toUpperCase() + ")", projRepo.findAll(sort), HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> buildResponse(String msg, Object data, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", msg);
        body.put("data", data);
        return new ResponseEntity<>(body, status);
    }
}
