package com.kuis11.kuis11.repository;

import com.kuis11.kuis11.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
