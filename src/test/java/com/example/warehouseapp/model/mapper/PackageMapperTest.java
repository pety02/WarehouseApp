package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PackageMapperTest {

    private PackageMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PackageMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();

        Package pkg = new Package();
        pkg.setId(id);
        pkg.setName("Box");
        pkg.setPiecesCount(12);

        PackageResponseDTO dto = mapper.mapToResponseDTO(pkg);

        assertEquals(id.toString(), dto.getId());
        assertEquals("Box", dto.getName());
        assertEquals(12, dto.getPiecesCount());
    }

    @Test
    void updatePackage_shouldUpdateMutableFields() {
        Package pkg = new Package();

        PackageUpdateRequestDTO dto = new PackageUpdateRequestDTO();
        dto.setName("Crate");
        dto.setPiecesCount(20);
        dto.setUpdatedBy("admin");
        dto.setUpdatedAt(Instant.now());

        mapper.updatePackage(pkg, dto);

        assertEquals("Crate", pkg.getName());
        assertEquals(20, pkg.getPiecesCount());
        assertEquals("admin", pkg.getUpdatedBy());
        assertNotNull(pkg.getUpdatedAt());
    }

    @Test
    void mapToEntity_shouldCreatePackageCorrectly() {
        PackageCreateRequestDTO dto = new PackageCreateRequestDTO();
        dto.setName("Bag");
        dto.setPiecesCount(5);
        dto.setCreatedBy("user");
        dto.setUpdatedBy("user");
        dto.setCreatedAt(Instant.now());
        dto.setUpdatedAt(Instant.now());

        Package pkg = mapper.mapToEntity(dto);

        assertEquals("Bag", pkg.getName());
        assertEquals(5, pkg.getPiecesCount());
        assertEquals("user", pkg.getCreatedBy());
    }
}
