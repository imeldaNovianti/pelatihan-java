package com.utsimelda.utsimelda.repository;

import com.utsimelda.utsimelda.model.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByTitleContainingIgnoreCase(String title, Sort sort);

    default List<Project> sortByTitleAsc() {
        return findAll(Sort.by("title").ascending());
    }

    default List<Project> sortByTitleDesc() {
        return findAll(Sort.by("title").descending());
    }
}
