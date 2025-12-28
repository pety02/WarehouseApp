package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.model.entites.ItemType;
import com.example.warehouseapp.repository.ItemTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class ItemTypeServiceIntegrationTest {

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Test
    void getAllItemTypes_realDb() {
        itemTypeRepository.save(
                ItemType.builder().name("Electronics").build()
        );

        List<ItemTypeResponseDTO> types =
                itemTypeService.getAllItemTypes();

        assertFalse(types.isEmpty());
    }
}
