package com.example.learn_system.dto.GroupDto;


import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class GroupDTO {
    private Long id;

    @JsonProperty(value = "start_date")
    private LocalDate startDate;

    @JsonProperty(value = "end_date")
    private LocalDate endDate;

    @JsonProperty(value = "course_id")
    private Long courseId;

    @JsonProperty(value = "course_name")
    private String courseName;

    private Set<SimpleEmployeeDTO> members; // сотрудники в группе
}