package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.repository.ItemRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class ItemServiceIntegrationTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void getItemById_realDb() {
        Item item = itemRepository.save(
                Item.builder().name("Test Item").build()
        );

        ItemResponseDTO dto = itemService.getItemById(item.getId());

        assertEquals(item.getId().toString(), dto.getId());
    }
}
