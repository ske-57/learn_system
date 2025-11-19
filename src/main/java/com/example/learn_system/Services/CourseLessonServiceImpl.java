package com.example.learn_system.Services;

import com.example.learn_system.Entity.Course;
import com.example.learn_system.Entity.CourseLesson;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Repository.CourseLessonRepository;
import com.example.learn_system.Repository.CourseRepository;
import com.example.learn_system.Services.interfaces.CourseLessonService;
import com.example.learn_system.dto.CourseDto.CourseCreateDTO;
import com.example.learn_system.dto.CourseDto.CourseWithLessonsDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonCreateDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonDTO;
import org.springframework.stereotype.Service;

@Service
public class CourseLessonServiceImpl implements CourseLessonService {

    private final CourseLessonRepository courseLessonRepository;
    private final CourseRepository courseRepository;


    public CourseLessonServiceImpl(CourseLessonRepository courseLessonRepository, CourseRepository courseRepository) {
        this.courseLessonRepository = courseLessonRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public CourseLessonDTO addLessonToCourse(Long courseId, CourseLessonCreateDTO req) {
        validate(req);

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("COURSE", courseId));

        CourseLesson lesson = toLessonEntity(req);

        lesson.setCourse(course);

        return toLessonResponse(courseLessonRepository.save(lesson));
    }

    private static CourseLessonDTO toLessonResponse(CourseLesson req) {
        CourseLessonDTO dto = new CourseLessonDTO();

        dto.setId(req.getId());
        dto.setName(req.getName());
        dto.setHours(req.getHours());
        dto.setCourseId(req.getCourse().getId());

        return dto;
    }

    private static CourseLesson toLessonEntity(CourseLessonCreateDTO req) {
        CourseLesson lesson = new CourseLesson();


        lesson.setName(req.getName());
        lesson.setHours(req.getHours());

        return lesson;
    }

    private void validate(CourseLessonCreateDTO req) {
        if (req.getName() == null || req.getName().isEmpty()) throw new ValidationException("Name can't be null or empty");
        if (req.getHours() == null) throw new ValidationException("Hours can't be empty");
        if (req.getHours() <= 0) throw new ValidationException("Hours cant be less than zero or equals it");
    }
}
