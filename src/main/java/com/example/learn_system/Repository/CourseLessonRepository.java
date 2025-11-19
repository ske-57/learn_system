package com.example.learn_system.Repository;

import com.example.learn_system.Entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
    List<CourseLesson> getCourseLessonsByCourseId(Long courseId);
}
