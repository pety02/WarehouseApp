package com.example.warehouseapp.model.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageCreateRequestDTO {
    private String name;
    private Integer piecesCount;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
}
