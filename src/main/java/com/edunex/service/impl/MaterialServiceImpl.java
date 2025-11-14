package com.edunex.service.impl;

import com.edunex.dto.MaterialAddDto;
import com.edunex.model.Material;
import com.edunex.repository.MaterialRepository;
import com.edunex.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Material addMaterial(MaterialAddDto dto) {
        Material material = Material.builder()
                .courseId(dto.getCourseId())
                .title(dto.getTitle())
                .pdfUrl(dto.getPdfUrl())
                .build();

        return materialRepository.save(material);
    }

    @Override
    public List<Material> getMaterialsByCourse(Long courseId) {
        return materialRepository.findByCourseId(courseId);
    }
}
