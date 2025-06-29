package com.utsimelda.utsimelda.repository;

import com.utsimelda.utsimelda.model.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Search by g
    List<Company> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address, Sort sort);

    // buat sorting manual kalo perlu
    default List<Company> sortByNameAsc() {
        return findAll(Sort.by("name").ascending());
    }

    default List<Company> sortByNameDesc() {
        return findAll(Sort.by("name").descending());
    }
}
