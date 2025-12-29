package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.StockAvailability;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StockAvailabilityMapper {

    public StockAvailability mapToEntity(
            StockAvailabilityResponseDTO dto,
            Item item,
            WarehouseZone zone
    ) {
        StockAvailability entity = new StockAvailability();
        entity.setPiecesCount(dto.getPiecesCount());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        entity.setCreatedAt(Instant.parse(dto.getCreatedAt()));
        entity.setUpdatedAt(Instant.parse(dto.getUpdatedAt()));
        entity.setItem(item);
        entity.setZone(zone);
        return entity;
    }

    public StockAvailabilityResponseDTO mapToResponseDTO(StockAvailability entity) {
        return StockAvailabilityResponseDTO.builder()
                .piecesCount(entity.getPiecesCount())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .createdAt(entity.getCreatedAt().toString())
                .updatedAt(entity.getUpdatedAt().toString())
                .item(entity.getItem().getId().toString())
                .warehouseZone(entity.getZone().getId().toString())
                .build();
    }
}
