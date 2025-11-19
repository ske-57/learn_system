package com.example.learn_system.Services;

import com.example.learn_system.Entity.Course;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Repository.CourseRepository;
import com.example.learn_system.Services.interfaces.CourseService;
import com.example.learn_system.dto.CourseDto.CourseCreateDTO;
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


    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> list = courseRepository.findAll();

        return list.stream()
                .map(CourseServiceImpl::toCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseCreateDTO req) {
        if (!validate(req)) throw new ValidationException("Course name is null or empty");

        Course course = toCourseEntity(req);

        return toCourseResponse(courseRepository.save(course));
    }

    @Override
    public CourseDTO getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("COURSE", id));

        return toCourseResponse(course);
    }


    private static CourseDTO toCourseResponse(Course c) {
        if (c == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setMark(c.getMark());
        dto.setHours(c.getHours());

        return dto;
    }

    private static Course toCourseEntity(CourseCreateDTO req) {
        Course course = new Course();
        course.setName(req.getName());
        course.setMark(req.getMark());

        return course;
    }

    private static boolean validate(CourseCreateDTO req) {
        return req.getName() != null && !req.getName().isEmpty();
    }
}
