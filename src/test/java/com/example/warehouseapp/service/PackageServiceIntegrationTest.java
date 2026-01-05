package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.repository.PackageRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class PackageServiceIntegrationTest {

    @Autowired
    private PackageService service;

    @Autowired
    private PackageRepository repository;

    @Test
    void createPackage_realDb() {
        PackageCreateRequestDTO dto =
                new PackageCreateRequestDTO();
        dto.setName("Box");

        PackageResponseDTO response = service.createPackage(dto, Instant.now(), "user");

        assertNotNull(response.getId());
    }
}
