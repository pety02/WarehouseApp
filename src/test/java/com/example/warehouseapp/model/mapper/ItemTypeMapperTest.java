package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.model.entites.ItemType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemTypeMapperTest {

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();

        ItemType itemType = new ItemType();
        itemType.setId(id);
        itemType.setName("Dairy");

        ItemTypeMapper mapper = new ItemTypeMapper();
        ItemTypeResponseDTO dto = mapper.mapToResponseDTO(itemType);

        assertEquals(id.toString(), dto.getId());
        assertEquals("Dairy", dto.getName());
    }
}
