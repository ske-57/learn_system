package com.example.learn_system.dto.EmployeeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleEmployeeDTO {
    private Long id;
    private String name;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "middle_name")
    private String middleName;

    private Long organizationId;

    @JsonProperty(value = "organization_name")
    private String organizationName;
}
