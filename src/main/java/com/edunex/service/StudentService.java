package com.edunex.service;

import com.edunex.dto.StudentSignupDto;
import com.edunex.model.Student;

public interface StudentService {
    Student saveStudent(StudentSignupDto dto);
}
