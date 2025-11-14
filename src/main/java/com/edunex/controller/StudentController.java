package com.edunex.controller;

import com.edunex.dto.StudentSignupDto;
import com.edunex.model.Student;
import com.edunex.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<Student> signup(@Valid @RequestBody StudentSignupDto dto) {
        Student saved = studentService.saveStudent(dto);
        return ResponseEntity.ok(saved);
    }

    // Just testing purpose se bnaya tha
    @GetMapping("/dashboard")
    public String studentDashboard() {
        return "Welcome Student — You can access only Student APIs ";
    }

}
