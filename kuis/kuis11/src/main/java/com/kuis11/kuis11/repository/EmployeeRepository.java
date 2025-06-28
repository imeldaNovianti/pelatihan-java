package com.kuis11.kuis11.repository;

import com.kuis11.kuis11.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // search
    List<Employee> findByNameContainingIgnoreCase(String name);
    // sorting
    List<Employee> findAllByOrderByNameAsc();
    List<Employee> findAllByOrderByNameDesc();
}
