package com.example.learn_system.Controllers;

import com.example.learn_system.Exceptions.BusinessException;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Services.CourseServiceImpl;
import com.example.learn_system.Services.interfaces.CourseLessonService;
import com.example.learn_system.Services.interfaces.CourseService;
import com.example.learn_system.dto.CourseDto.CourseCreateDTO;
import com.example.learn_system.dto.CourseDto.CourseDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonCreateDTO;
import com.example.learn_system.dto.CourseLessonDto.CourseLessonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/courses")
@Tag(name = "Course controller")
public class CourseController {

    private final CourseService courseService;
    private final CourseLessonService courseLessonService;

    public CourseController(CourseServiceImpl courseService, CourseLessonService courseLessonService) {
        this.courseService = courseService;
        this.courseLessonService = courseLessonService;
    }

    @GetMapping
    @Operation(description = "Get all courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> result = courseService.getAllCourses();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(description = "Create new course")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseCreateDTO req) {

        try {
            CourseDTO created = courseService.createCourse(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ValidationException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("errorCode", ex.getErrorCode());
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @GetMapping(value = "{courseId}")
    @Operation(description = "Get course by ID")
    public ResponseEntity<?> getCourseById(@Valid @PathVariable Long courseId) {
        try {
            CourseDTO courseDTO = courseService.getById(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionToJson(ex));
        }
    }

    @GetMapping(value = "{courseId}/lessons")
    @Operation(description = "Get course lessons by courseID")
    public ResponseEntity<?> getCourseLessonsById(@Valid @PathVariable Long courseId) {

        try {
            List<CourseLessonDTO> result = courseLessonService.getCourseLessonsByCourseId(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionToJson(ex));
        }

    }

    @PostMapping(value = "{courseId}/lessons")
    @Operation(description = "Adding one lesson to course")
    public ResponseEntity<?> addLessonToCourse(@Valid @PathVariable Long courseId, @Valid @RequestBody CourseLessonCreateDTO req) {

        try {
            CourseLessonDTO lesson = courseLessonService.addLessonToCourse(courseId, req);
            courseService.updateCourseHours(courseId, lesson.getHours());
            return ResponseEntity.status(HttpStatus.CREATED).body(lesson);

        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionToJson(ex));

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionToJson(ex));
        }
    }

    private static HashMap<String, String> exceptionToJson(BusinessException ex) {
        HashMap<String, String> error = new HashMap<>();
        error.put("errorCode", ex.getErrorCode());
        error.put("message", ex.getMessage());
        return error;
    }
}
