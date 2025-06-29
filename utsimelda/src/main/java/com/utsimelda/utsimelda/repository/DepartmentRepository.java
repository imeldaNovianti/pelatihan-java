package com.utsimelda.utsimelda.repository;

import com.utsimelda.utsimelda.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByNameContainingIgnoreCase(String name, Sort sort);

    default List<Department> sortByNameAsc() {
        return findAll(Sort.by("name").ascending());
    }

    default List<Department> sortByNameDesc() {
        return findAll(Sort.by("name").descending());
    }
}
