package com.example.learn_system.dto.GroupDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateGroupDTO {
    @NotNull(message = "Group id is required")
    private Long id;

    @NotNull(message = "Start date is required")
    @JsonProperty(value = "start_date")
    private LocalDate startDate;

    @JsonProperty(value = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Course id is required")
    @JsonProperty(value = "course_id")
    private Long courseId;

}
