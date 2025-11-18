package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.CourseDto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();
}
