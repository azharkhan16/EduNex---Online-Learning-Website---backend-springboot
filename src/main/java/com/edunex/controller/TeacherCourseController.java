package com.edunex.controller;

import com.edunex.dto.CourseAddDto;
import com.edunex.model.Course;
import com.edunex.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/course")
@RequiredArgsConstructor
public class TeacherCourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(
            @RequestBody CourseAddDto dto,
            Authentication auth
    ) {
        Long teacherId = null;                 // extract teacher ID if needed
        return ResponseEntity.ok(courseService.addCourse(dto, teacherId));
    }
}
