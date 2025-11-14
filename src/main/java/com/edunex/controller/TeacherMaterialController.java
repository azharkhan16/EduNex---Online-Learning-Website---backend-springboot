package com.edunex.controller;

import com.edunex.dto.MaterialAddDto;
import com.edunex.model.Material;
import com.edunex.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/material")
@RequiredArgsConstructor
public class TeacherMaterialController {

    private final MaterialService materialService;

    @PostMapping("/add")
    public ResponseEntity<Material> addMaterial(@RequestBody MaterialAddDto dto) {
        return ResponseEntity.ok(materialService.addMaterial(dto));
    }
}
