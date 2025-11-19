package com.example.learn_system.dto.CourseLessonDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseLessonCreateDTO {

    @NotBlank(message = "Course lesson name is not optional")
    private String name;

    @NotBlank(message = "Course lesson hours is not optional")
    private Long hours;
}
