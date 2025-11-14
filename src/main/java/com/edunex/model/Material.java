package com.edunex.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;      // which course this material belongs to

    private String title;

    private String pdfUrl;      // OR video URL, notes URL etc.

    @CreationTimestamp
    private LocalDateTime createdAt;
}
