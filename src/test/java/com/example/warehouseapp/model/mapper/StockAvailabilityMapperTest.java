package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.StockAvailability;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StockAvailabilityMapperTest {

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        Item item = new Item();
        item.setId(UUID.randomUUID());

        WarehouseZone zone = new WarehouseZone();
        zone.setId(UUID.randomUUID());

        StockAvailability availability = new StockAvailability();
        availability.setPiecesCount(25);
        availability.setItem(item);
        availability.setZone(zone);

        StockAvailabilityMapper mapper = new StockAvailabilityMapper();
        StockAvailabilityResponseDTO dto = mapper.mapToResponseDTO(availability);

        assertEquals(25, dto.getPiecesCount());
        assertEquals(item.getId().toString(), dto.getItem());
        assertEquals(zone.getId().toString(), dto.getWarehouseZone());
    }

    @Test
    void mapToEntity_shouldCreateEntityCorrectly() {
        StockAvailabilityResponseDTO dto = StockAvailabilityResponseDTO.builder()
                .piecesCount(50)
                .createdBy("user")
                .updatedBy("user")
                .createdAt(Instant.now().toString())
                .updatedAt(Instant.now().toString())
                .build();

        Item item = new Item();
        WarehouseZone zone = new WarehouseZone();

        StockAvailabilityMapper mapper = new StockAvailabilityMapper();
        StockAvailability entity = mapper.mapToEntity(dto, item, zone);

        assertEquals(50, entity.getPiecesCount());
        assertEquals(item, entity.getItem());
        assertEquals(zone, entity.getZone());
    }
}
