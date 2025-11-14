package com.edunex.service.impl;

import com.edunex.dto.CourseAddDto;
import com.edunex.model.Course;
import com.edunex.repository.CourseRepository;
import com.edunex.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course addCourse(CourseAddDto dto, Long teacherId) {

        Course course = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .videoUrl(dto.getVideoUrl())
                .teacherId(teacherId)
                .build();

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
