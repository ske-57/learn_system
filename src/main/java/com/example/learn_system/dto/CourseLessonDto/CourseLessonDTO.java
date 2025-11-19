package com.example.learn_system.dto.CourseLessonDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseLessonDTO {

    private Long id;
    private String name;
    private Long hours;

    @JsonProperty(value = "course_id")
    private Long courseId;

}
