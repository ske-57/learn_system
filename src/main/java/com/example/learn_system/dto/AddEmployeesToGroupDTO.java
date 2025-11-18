package com.example.learn_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class AddEmployeesToGroupDTO {
    @NotNull(message = "Group id is required")
    private Long groupId;

    @NotNull
    @Size(min = 1, message = "Employee id is required")
    private Long employeeId;
}