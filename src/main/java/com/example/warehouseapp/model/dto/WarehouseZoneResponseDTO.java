package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseZoneResponseDTO {
    private String id;
    private String name;
    private String storageTypeName;
}
