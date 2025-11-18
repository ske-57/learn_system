package com.example.learn_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 512)
    private String name;

    @Column(name = "hours")
    private Long hours;

    @Column(name = "mark", length = 32)
    private String mark;
}
