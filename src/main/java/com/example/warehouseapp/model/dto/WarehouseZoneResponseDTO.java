package com.example.warehouseapp.model.dto;

import lombok.*;
import java.util.List;

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
    private List<LocationResponseDTO> locations;
}
