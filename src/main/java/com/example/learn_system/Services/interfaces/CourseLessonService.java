package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.CourseDto.CourseDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonCreateDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonDTO;

import java.util.List;

public interface CourseLessonService {
    CourseLessonDTO addLessonToCourse(Long courseId, CourseLessonCreateDTO req);
    List<CourseLessonDTO> getCourseLessonsByCourseId(Long courseId);
}
