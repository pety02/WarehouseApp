package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO;
import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO;
import com.example.warehouseapp.model.entites.StorageType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StorageTypeMapperTest {

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        StorageType type = new StorageType();
        type.setId(UUID.randomUUID());
        type.setName("Frozen");

        StorageTypeMapper mapper = new StorageTypeMapper();
        StorageTypeResponseDTO dto = mapper.mapToResponseDTO(type);

        assertEquals(type.getId().toString(), dto.getId());
        assertEquals("Frozen", dto.getName());
    }

    @Test
    void mapToEntity_shouldCreateStorageType() {
        StorageTypeCreateRequestDTO dto = new StorageTypeCreateRequestDTO();
        dto.setName("Dry");

        StorageTypeMapper mapper = new StorageTypeMapper();
        StorageType entity = mapper.mapToEntity(dto, "admin", LocalDate.now());

        assertEquals("Dry", entity.getName());
        assertEquals("admin", entity.getCreatedBy());
    }

    @Test
    void updateEntity_shouldUpdateFields() {
        StorageType type = new StorageType();
        StorageTypeUpdateRequestDTO dto = new StorageTypeUpdateRequestDTO();
        dto.setName("Cold");

        StorageTypeMapper mapper = new StorageTypeMapper();
        mapper.updateEntity(type, dto, "user", LocalDate.now());

        assertEquals("Cold", type.getName());
        assertEquals("user", type.getUpdatedBy());
    }
}
