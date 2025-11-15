package com.edunex.service.impl;

import com.edunex.dto.TeacherSignupDto;
import com.edunex.exception.EmailAlreadyExistsException;
import com.edunex.model.Role;
import com.edunex.model.Teacher;
import com.edunex.repository.TeacherRepository;
import com.edunex.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Teacher saveTeacher(TeacherSignupDto dto) {
        // STEP 1 - Duplicate Email Check
        if (teacherRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists ! Try with another Email");
        }
        // STEP 2 - Normal save logic
        Teacher teacher = Teacher.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.TEACHER)
                .build();

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public boolean deleteTeacher(Long id) {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
