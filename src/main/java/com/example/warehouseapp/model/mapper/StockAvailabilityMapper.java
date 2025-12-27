package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.StockAvailability;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StockAvailabilityMapper {

    public StockAvailabilityResponseDTO mapToResponseDTO(StockAvailability stockAvailability){
        return StockAvailabilityResponseDTO
                .builder()
                .piecesCount(stockAvailability.getPiecesCount())
                .item(stockAvailability.getItem().getId().toString())
                .warehouseZone(stockAvailability.getZone().getId().toString())
                .build();
    }

    public StockAvailability mapToEntity(StockAvailabilityResponseDTO stockAvailabilityResponseDTO, Item item, WarehouseZone zone) {
        return StockAvailability
                .builder()
                .piecesCount(stockAvailabilityResponseDTO.getPiecesCount())
                .createdBy(stockAvailabilityResponseDTO.getCreatedBy())
                .updatedBy(stockAvailabilityResponseDTO.getUpdatedBy())
                .createdAt(Instant.parse(stockAvailabilityResponseDTO.getCreatedAt()))
                .updatedAt(Instant.parse(stockAvailabilityResponseDTO.getUpdatedAt()))
                .item(item)
                .zone(zone)
                .build();

    }
}
