package com.utsimelda.utsimelda.repository;

import com.utsimelda.utsimelda.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFullNameContainingIgnoreCase(String keyword);

    List<Employee> findByFullNameContainingIgnoreCase(String keyword, Sort sort);

    default List<Employee> sortByNameAsc() {
        return findAll(Sort.by("fullName").ascending());
    }

    default List<Employee> sortByNameDesc() {
        return findAll(Sort.by("fullName").descending());
    }
}
