package com.example.learn_system.dto.CourseDto;

import com.example.learn_system.dto.CourseLessonDto.CourseLessonDTO;
import lombok.Data;

import java.util.List;

@Data
public class CourseWithLessonsDTO {
    private Long id;
    private String name;
    private Long hours;
    private String mark;
    private List<CourseLessonDTO> lessons;
}
