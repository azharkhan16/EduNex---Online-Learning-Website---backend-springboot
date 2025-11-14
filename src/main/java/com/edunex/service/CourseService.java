package com.edunex.service;

import com.edunex.dto.CourseAddDto;
import com.edunex.model.Course;
import java.util.List;

public interface CourseService {
    Course addCourse(CourseAddDto dto, Long teacherId);
    List<Course> getAllCourses();
}
