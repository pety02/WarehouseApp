package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAvailabilityResponseDTO {
    private Integer piecesCount;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private String item;
    private String warehouseZone;
}
