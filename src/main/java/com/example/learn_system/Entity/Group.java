package com.example.learn_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "groups")
// TODO разобраться с каскадами (нужны / нет)
public class Group {
    @Id
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // С этой строкой может быть рекурсия (потом протестить)
//    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY)
//    private Set<Employee> members;
}
