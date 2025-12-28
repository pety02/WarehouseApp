package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.entites.PackageType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PackageTypeMapperTest {

    @Test
    void mapToPackageType_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();

        PackageType type = new PackageType();
        type.setId(id);
        type.setName("Bottle");

        PackageTypeMapper mapper = new PackageTypeMapper();
        PackageTypeResponseDTO dto = mapper.mapToPackageType(type);

        assertEquals(id.toString(), dto.getId());
        assertEquals("Bottle", dto.getName());
    }

    @Test
    void mapToEntity_shouldCreateEntityCorrectly() {
        PackageTypeRequestDTO dto = new PackageTypeRequestDTO();
        dto.setName("Crate");

        PackageTypeMapper mapper = new PackageTypeMapper();
        PackageType entity = mapper.mapToEntity(dto);

        assertEquals("Crate", entity.getName());
    }
}
