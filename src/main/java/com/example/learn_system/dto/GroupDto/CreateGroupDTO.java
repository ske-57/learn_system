package com.example.learn_system.dto.GroupDto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateGroupDTO {
    @NotNull(message = "Group id is required")
    private Long id;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Course id is required")
    private Long courseId;

}
