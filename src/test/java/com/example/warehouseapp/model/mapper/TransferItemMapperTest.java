package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferItemMapperTest {

    @Test
    void mapToEntity_shouldCreateTransferItem() {
        TransferItemCreateRequestDTO dto = new TransferItemCreateRequestDTO();
        dto.setQuantity(10);

        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName("Juice");

        Transfer transfer = new Transfer();

        TransferItemMapper mapper = new TransferItemMapper();
        TransferItem entity = mapper.mapToEntity(dto, transfer, item, "user");

        assertEquals(10, entity.getQuantity());
        assertEquals(item, entity.getItem());
        assertEquals("user", entity.getCreatedBy());
    }

    @Test
    void updateTransferItem_shouldUpdateFields() {
        TransferItem item = new TransferItem();

        Item newItem = new Item();
        TransferItemUpdateRequestDTO dto = new TransferItemUpdateRequestDTO();
        dto.setQuantity(20);

        TransferItemMapper mapper = new TransferItemMapper();
        mapper.updateTransferItem(item, newItem, dto, "admin");

        assertEquals(20, item.getQuantity());
        assertEquals(newItem, item.getItem());
        assertEquals("admin", item.getUpdatedBy());
    }
}
