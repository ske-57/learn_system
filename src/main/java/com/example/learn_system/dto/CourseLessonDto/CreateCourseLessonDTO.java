package com.example.learn_system.dto.CourseLessonDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCourseLessonDTO {

    @NotBlank(message = "Course lesson name is not optional")
    private String name;

    @NotBlank(message = "Course lesson hours is not optional")
    private Long hours;

    @NotNull(message = "Course id for course lesson is not optional")
    private Long courseId;
}
