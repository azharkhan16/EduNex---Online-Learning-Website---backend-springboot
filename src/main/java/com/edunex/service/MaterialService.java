package com.edunex.service;

import com.edunex.dto.MaterialAddDto;
import com.edunex.model.Material;

import java.util.List;

public interface MaterialService {
    Material addMaterial(MaterialAddDto dto);
    List<Material> getMaterialsByCourse(Long courseId);
}
