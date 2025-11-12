package com.edunex.service;

import com.edunex.dto.TeacherSignupDto;
import com.edunex.model.Teacher;

public interface TeacherService {
    Teacher saveTeacher(TeacherSignupDto dto);
}
