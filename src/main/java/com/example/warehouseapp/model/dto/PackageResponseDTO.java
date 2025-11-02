package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageResponseDTO {
    private String id;
    private String name;
    private Integer piecesCount;
}
