package com.edunex.controller;

import com.edunex.dto.TeacherSignupDto;
import com.edunex.model.Teacher;
import com.edunex.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/signup")
    public ResponseEntity<Teacher> signup(@Valid @RequestBody TeacherSignupDto dto) {
        Teacher saved = teacherService.saveTeacher(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/dashboard")
    public String teacherDashboard() {
        return "Welcome Teacher — You can access Teacher & Student APIs ";
    }

}
