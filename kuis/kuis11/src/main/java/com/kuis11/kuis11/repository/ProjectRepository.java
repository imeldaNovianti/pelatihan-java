package com.kuis11.kuis11.repository;

import com.kuis11.kuis11.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
