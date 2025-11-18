package com.example.learn_system.dto.CourseLessonDto;

import lombok.Data;

@Data
public class CourseLessonDTO {

    private Long id;
    private String name;
    private Long hours;
    private Long courseId;

}
