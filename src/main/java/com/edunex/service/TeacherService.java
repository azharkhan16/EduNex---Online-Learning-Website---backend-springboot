package com.edunex.service;

import com.edunex.dto.TeacherSignupDto;
import com.edunex.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(TeacherSignupDto dto);
    List<Teacher> getAllTeachers();
    boolean deleteTeacher(Long id);

}
