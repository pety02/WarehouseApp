package com.example.warehouseapp.service;

import com.example.warehouseapp.repository.StorageTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class StorageTypeServiceIntegrationTest {

    @Autowired
    private StorageTypeService service;
    @Autowired
    private StorageTypeRepository repository;

    @Test
    void createAndFetchStorageType() {
        var request = new com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO();
        request.setName("CoolStorage");

        var dto = service.createStorageType(request, "admin");
        assertNotNull(dto);
        assertEquals("CoolStorage", dto.getName());
    }
}
