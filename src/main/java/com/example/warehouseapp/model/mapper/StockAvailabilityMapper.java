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
                .itemId(stockAvailability.getItem().getId().toString())
                .itemName(stockAvailability.getItem().getName())
                .itemBarcodeValue(stockAvailability.getItem().getBarcodeValue())
                .itemExpirationDateTime(stockAvailability.getItem().getExpirationDateTime().toString())
                .zoneId(stockAvailability.getZone().getId().toString())
                .zoneName(stockAvailability.getZone().getName())
                .zoneStorageType(stockAvailability.getZone().getStorageType().toString())
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
