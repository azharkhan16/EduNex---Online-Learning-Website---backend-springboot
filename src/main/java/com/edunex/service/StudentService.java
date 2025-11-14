package com.edunex.service;

import com.edunex.dto.StudentSignupDto;
import com.edunex.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(StudentSignupDto dto);
    List<Student> getAllStudents();
    boolean deleteStudent(Long id);

}
