package com.example.learn_system.Controllers;

import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Services.CourseServiceImpl;
import com.example.learn_system.dto.CourseDto.CourseCreateDTO;
import com.example.learn_system.dto.CourseDto.CourseDTO;
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

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
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
}
