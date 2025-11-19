package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.CourseDto.CourseCreateDTO;
import com.example.learn_system.dto.CourseDto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO createCourse(CourseCreateDTO req);
    CourseDTO getById(Long id);
    boolean existsById(Long id);
}
