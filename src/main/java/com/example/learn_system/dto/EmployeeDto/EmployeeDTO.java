package com.example.learn_system.dto.EmployeeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "middle_name")
    private String middleName;

    private String snils;

    @JsonProperty(value = "birth_date")
    private LocalDate birthDate;

    private String grade;

    @JsonProperty(value = "organization_id")
    private Long organizationId;

    @JsonProperty(value = "organization_name")
    private String organizationName;

    private String phone;
    private String email;
    private String education;

    @JsonProperty(value = "is_active")
    private Boolean isActive = true;
}
