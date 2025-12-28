package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO;
import com.example.warehouseapp.model.entites.StorageType;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseZoneMapperTest {

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        StorageType storageType = new StorageType();
        storageType.setName("Frozen");

        WarehouseZone zone = new WarehouseZone();
        zone.setId(UUID.randomUUID());
        zone.setName("Frozen Zone");
        zone.setStorageType(storageType);

        WarehouseZoneMapper mapper = new WarehouseZoneMapper();
        WarehouseZoneResponseDTO dto = mapper.mapToResponseDTO(zone);

        assertEquals(zone.getId().toString(), dto.getId());
        assertEquals("Frozen Zone", dto.getName());
        assertEquals("Frozen", dto.getStorageTypeName());
    }
}
