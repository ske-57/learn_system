package com.example.learn_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "course_lessons")
// TODO разобраться с каскадами (нужны / нет)
public class CourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 512)
    private String name;

    @Column(name = "hours", nullable = false)
    private Long hours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
