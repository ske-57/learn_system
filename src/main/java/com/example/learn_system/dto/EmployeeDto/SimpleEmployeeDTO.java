package com.example.learn_system.dto.EmployeeDto;

import lombok.Data;

@Data
public class SimpleEmployeeDTO {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String organizationName;
}
