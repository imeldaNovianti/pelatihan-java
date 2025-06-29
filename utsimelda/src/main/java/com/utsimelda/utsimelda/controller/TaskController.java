package com.utsimelda.utsimelda.controller;

import com.utsimelda.utsimelda.model.Task;
import com.utsimelda.utsimelda.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepo;


    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        return buildResponse("Daftar task", taskRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return taskRepo.findById(id)
                .map(task -> buildResponse("Detail task", task, HttpStatus.OK))
                .orElse(buildResponse("Task tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Task task) {
        return buildResponse("Task berhasil dibuat", taskRepo.save(task), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Task input) {
        return taskRepo.findById(id)
                .map(existing -> {
                    existing.setName(input.getName());
                    existing.setStatus(input.getStatus());
                    existing.setProject(input.getProject());
                    return buildResponse("Task berhasil diperbarui", taskRepo.save(existing), HttpStatus.OK);
                })
                .orElse(buildResponse("Task tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return taskRepo.findById(id)
                .map(existing -> {
                    taskRepo.delete(existing);
                    return buildResponse("Task berhasil dihapus", null, HttpStatus.OK);
                })
                .orElse(buildResponse("Task tidak ditemukan", null, HttpStatus.NOT_FOUND));
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        Sort sortOrder = sort.equalsIgnoreCase("desc") ?
                Sort.by("name").descending() : Sort.by("name").ascending();

        List<Task> tasks = taskRepo.findAll(sortOrder);

        if (name != null) {
            tasks = tasks.stream()
                    .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (status != null) {
            tasks = tasks.stream()
                    .filter(t -> t.getStatus().equalsIgnoreCase(status))
                    .toList();
        }

        return buildResponse("Hasil pencarian task", tasks, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sortByName(@RequestParam(defaultValue = "asc") String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by("name").descending() : Sort.by("name").ascending();

        return buildResponse("Task diurutkan berdasarkan nama (" + order.toUpperCase() + ")", taskRepo.findAll(sort), HttpStatus.OK);
    }


    private ResponseEntity<Map<String, Object>> buildResponse(String msg, Object data, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", msg);
        body.put("data", data);
        return new ResponseEntity<>(body, status);
    }
}
