package com.utsimelda.utsimelda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;   

    /** Many tasks ↔ one project */
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference                 // ← balik ke project
    private Project project;
}
