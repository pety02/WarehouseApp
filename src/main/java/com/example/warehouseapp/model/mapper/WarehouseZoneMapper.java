package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO;
import com.example.warehouseapp.model.entites.WarehouseZone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarehouseZoneMapper {
    private final LocationMapper locationMapper;

    public WarehouseZoneResponseDTO mapToResponseDTO(WarehouseZone warehouseZone){
        return WarehouseZoneResponseDTO
                .builder()
                .id(warehouseZone.getId().toString())
                .name(warehouseZone.getName())
                .storageTypeName(warehouseZone.getStorageType().getName())
                .locations(warehouseZone.getLocations()
                        .stream()
                        .map(this.locationMapper::mapToResponseDTO).toList())
                .build();
    }
}
