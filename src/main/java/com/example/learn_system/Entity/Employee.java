package com.example.learn_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "employees")
// TODO разобраться с каскадами (нужны / нет)
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;

    @Column(name = "middle_name", length = 256)
    private String middleName;

    @Column(name = "snils", length = 32)
    private String snils;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "grade", length = 512)
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "education", nullable = false, length = 512)
    private String education;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "group_members",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;

}
