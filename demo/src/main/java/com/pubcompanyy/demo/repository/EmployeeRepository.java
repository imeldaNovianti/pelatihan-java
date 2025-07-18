package com.pubcompanyy.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubcompanyy.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
    List<Employee> findEmployeeByEmail(String email);
    Optional<Employee> findByEmail(String email);
}