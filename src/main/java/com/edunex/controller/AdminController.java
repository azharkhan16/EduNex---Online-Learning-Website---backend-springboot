package com.edunex.controller;

import com.edunex.dto.StudentSignupDto;
import com.edunex.dto.TeacherSignupDto;
import com.edunex.model.Student;
import com.edunex.model.Teacher;
import com.edunex.service.StudentService;
import com.edunex.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        Map<String, Object> result = new HashMap<>();

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();

        result.put("students", students);
        result.put("teachers", teachers);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        boolean removed = studentService.deleteStudent(id);
        return ResponseEntity.ok(Map.of("deleted", removed));
    }

    @DeleteMapping("/delete-teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        boolean removed = teacherService.deleteTeacher(id);
        return ResponseEntity.ok(Map.of("deleted", removed));
    }

    // It means Admin can add teacher
    @PostMapping("/add-teacher")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody TeacherSignupDto dto) {
        return ResponseEntity.ok(teacherService.saveTeacher(dto));
    }

    // It means Admin can add student
    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentSignupDto dto) {
        return ResponseEntity.ok(studentService.saveStudent(dto));
    }

    // Just testing purpose se bnaya tha
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin — Full access granted";
    }
}





