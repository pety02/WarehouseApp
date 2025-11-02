package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.stereotype.Component;

@Component
public class WarehouseZoneMapper {

    public WarehouseZoneResponseDTO mapToResponseDTO(WarehouseZone warehouseZone){
        return WarehouseZoneResponseDTO
                .builder()
                .id(warehouseZone.getId().toString())
                .name(warehouseZone.getName())
                .storageTypeName(warehouseZone.getStorageType().getName())
                .build();
    }
}
