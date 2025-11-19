package com.example.learn_system.Repository;

import com.example.learn_system.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE courses SET hours = COALESCE(hours, 0) + ?2 WHERE id = ?1")
    void updateCourseHours(Long courseId, Long hours);
}
