package com.kuis11.kuis11.repository;

import com.kuis11.kuis11.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
