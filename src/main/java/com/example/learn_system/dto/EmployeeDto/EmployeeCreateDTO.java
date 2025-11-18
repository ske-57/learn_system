package com.example.learn_system.dto.EmployeeDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    private String middleName;
    private String snils;
    private LocalDate birthDate;
    private String grade;

    @NotNull(message = "Organization id is required")
    private Long organizationId;

    private String phone;

    @Email(message = "Email is incorrect")
    private String email;

    @NotBlank(message = "Education is required")
    private String education;

    private Boolean isActive = true;
}
