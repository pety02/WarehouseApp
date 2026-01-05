package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.repository.PackageTypeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class PackageTypeServiceIntegrationTest {

    @Autowired
    private PackageTypeService service;

    @Autowired
    private PackageTypeRepository repository;

    @Test
    void createAndFetch_realDb() {
        PackageTypeRequestDTO dto = new PackageTypeRequestDTO("Bottle");

        PackageTypeResponseDTO created = service.createPackageType(dto, Instant.now(), "user");

        PackageTypeResponseDTO fetched =
                service.getPackageTypeById(UUID.fromString(created.getId()));

        assertEquals("Bottle", fetched.getName());
    }
}
