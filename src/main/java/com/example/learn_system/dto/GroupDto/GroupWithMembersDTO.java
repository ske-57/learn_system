package com.example.learn_system.dto.GroupDto;

import com.example.learn_system.dto.CourseDto.CourseDTO;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class GroupWithMembersDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseDTO course;
    private Set<EmployeeDTO> members;
}
