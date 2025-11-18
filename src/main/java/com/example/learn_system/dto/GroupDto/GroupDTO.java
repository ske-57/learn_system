package com.example.learn_system.dto.GroupDto;


import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class GroupDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long courseId;
    private Set<SimpleEmployeeDTO> members; // сотрудники в группе
}