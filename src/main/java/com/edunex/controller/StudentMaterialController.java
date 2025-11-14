package com.edunex.controller;

import com.edunex.model.Material;
import com.edunex.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/material")
@RequiredArgsConstructor
public class StudentMaterialController {

    private final MaterialService materialService;

    @GetMapping("/{courseId}")
    public ResponseEntity<List<Material>> getMaterials(@PathVariable Long courseId) {
        return ResponseEntity.ok(materialService.getMaterialsByCourse(courseId));
    }
}
