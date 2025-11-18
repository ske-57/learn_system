package com.example.learn_system.dto.EmployeeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "middle_name")
    private String middleName;

    private String snils;

    @JsonProperty(value = "birth_date")
    private LocalDate birthDate;

    private String grade;

    @NotNull(message = "Organization id is required")
    @JsonProperty(value = "organization_id")
    private Long organizationId;

    private String phone;

    @Email(message = "Email is incorrect")
    private String email;

    @NotBlank(message = "Education is required")
    private String education;

    @JsonProperty(value = "is_active")
    private Boolean isActive = true;
}
