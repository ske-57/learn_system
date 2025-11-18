package com.example.learn_system.dto.EmployeeDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String snils;
    private LocalDate birthDate;
    private String grade;
    private Long organizationId;
    private String phone;
    private String email;
    private String education;
    private Boolean isActive;
}
