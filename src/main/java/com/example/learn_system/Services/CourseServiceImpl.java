package com.example.learn_system.Services;

import com.example.learn_system.Entity.Course;
import com.example.learn_system.Repository.CourseRepository;
import com.example.learn_system.Services.interfaces.CourseService;
import com.example.learn_system.dto.CourseDto.CourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private static CourseDTO toResponse(Course c) {
        if (c == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setMark(c.getMark());
        dto.setHours(c.getHours());

        return dto;
    }


    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> list = courseRepository.findAll();

        return list.stream()
                .map(CourseServiceImpl::toResponse)
                .collect(Collectors.toList());
    }
}
