package com.kuis11.kuis11.controller;

import com.kuis11.kuis11.model.*;
import com.kuis11.kuis11.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository projectRepo;
    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public ProjectController(ProjectRepository projectRepo, EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Project r) {
        Map<String, Object> res = new HashMap<>();
        if (r.getEmployee() == null || r.getDepartment() == null) {
            res.put("message", "Employee dan Department wajib diisi");
            return ResponseEntity.badRequest().body(res);
        }

        Optional<Employee> emp = employeeRepo.findById(r.getEmployee().getId());
        Optional<Department> dep = departmentRepo.findById(r.getDepartment().getId());

        if (emp.isEmpty() || dep.isEmpty()) {
            res.put("message", "Employee atau Department tidak ditemukan");
            return ResponseEntity.badRequest().body(res);
        }

        r.setEmployee(emp.get());
        r.setDepartment(dep.get());
        res.put("data", projectRepo.save(r));
        res.put("message", "Project berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> res = new HashMap<>();
        res.put("data", projectRepo.findAll());
        res.put("message", "Semua project berhasil diambil");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        Optional<Project> data = projectRepo.findById(id);
        if (data.isPresent()) {
            res.put("data", data.get());
            res.put("message", "Project ditemukan");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Project tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Project r) {
        Map<String, Object> res = new HashMap<>();
        Optional<Project> data = projectRepo.findById(id);
        if (data.isPresent()) {
            Project p = data.get();
            p.setTitle(r.getTitle());

            if (r.getEmployee() != null) {
                Optional<Employee> e = employeeRepo.findById(r.getEmployee().getId());
                if (e.isEmpty()) {
                    res.put("message", "Employee tidak ditemukan");
                    return ResponseEntity.badRequest().body(res);
                }
                p.setEmployee(e.get());
            }

            if (r.getDepartment() != null) {
                Optional<Department> d = departmentRepo.findById(r.getDepartment().getId());
                if (d.isEmpty()) {
                    res.put("message", "Department tidak ditemukan");
                    return ResponseEntity.badRequest().body(res);
                }
                p.setDepartment(d.get());
            }

            res.put("data", projectRepo.save(p));
            res.put("message", "Project berhasil diperbarui");
            return ResponseEntity.ok(res);
        } else {
            res.put("message", "Project tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        if (!projectRepo.existsById(id)) {
            res.put("message", "Project tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        projectRepo.deleteById(id);
        res.put("message", "Project berhasil dihapus");
        return ResponseEntity.ok(res);
    }
}
