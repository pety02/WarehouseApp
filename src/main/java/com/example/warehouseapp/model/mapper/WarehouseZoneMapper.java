package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.StockAvailability;
import org.springframework.stereotype.Component;

@Component
public class WarehouseZoneMapper {

    public StockAvailabilityResponseDTO mapToResponseDTO(StockAvailability stockAvailability){
        return StockAvailabilityResponseDTO
                .builder()
                .id(stockAvailability.getId().toString())
                .piecesCount(stockAvailability.getPiecesCount())
                .itemId(stockAvailability.getItem().getId().toString())
                .itemName(stockAvailability.getItem().getName())
                .itemBarcodeValue(stockAvailability.getItem().getBarcodeValue())
                .itemExpirationDateTime(stockAvailability.getItem().getExpirationDateTime().toString())
                .zoneId(stockAvailability.getZone().getId().toString())
                .zoneName(stockAvailability.getZone().getName())
                .zoneStorageType(stockAvailability.getZone().getStorageType().getName())
                .build();
    }
}
