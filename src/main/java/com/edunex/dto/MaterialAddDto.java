package com.edunex.dto;

import lombok.Data;

@Data
public class MaterialAddDto {
    private Long courseId;
    private String title;
    private String pdfUrl;
}
