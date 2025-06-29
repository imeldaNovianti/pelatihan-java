package com.utsimelda.utsimelda.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;   

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
