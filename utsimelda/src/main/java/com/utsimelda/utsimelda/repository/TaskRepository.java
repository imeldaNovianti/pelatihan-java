package com.utsimelda.utsimelda.repository;

import com.utsimelda.utsimelda.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByNameContainingIgnoreCaseOrStatusContainingIgnoreCase(String name, String status, Sort sort);

    default List<Task> sortByNameAsc() {
        return findAll(Sort.by("name").ascending());
    }

    default List<Task> sortByNameDesc() {
        return findAll(Sort.by("name").descending());
    }
}
