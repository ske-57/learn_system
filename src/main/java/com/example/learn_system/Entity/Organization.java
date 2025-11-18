package com.example.learn_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false , length = 256)
    private String name;
}
