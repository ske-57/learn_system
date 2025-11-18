package com.example.learn_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "groups")
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

    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "group_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "employee_id", nullable = false)
    )
    private Set<Employee> members;
}
