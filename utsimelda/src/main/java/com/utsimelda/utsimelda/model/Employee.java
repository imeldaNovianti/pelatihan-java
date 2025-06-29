package com.utsimelda.utsimelda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String role;

    /** Many employees ↔ one department */
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference                 // ← balik ke department
    private Department department;
}
