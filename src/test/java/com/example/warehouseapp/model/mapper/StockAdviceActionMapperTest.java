package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAdviceActionResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.StockAdviceAction;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StockAdviceActionMapperTest {

    @Test
    void mapToResponseDTO_shouldMapAllFieldsCorrectly() {
        UUID actionId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        Item item = new Item();
        item.setId(itemId);
        item.setName("Milk");
        item.setBarcodeValue("987654321");
        item.setExpirationDateTime(Instant.now());

        StockAdviceAction action = new StockAdviceAction();
        action.setId(actionId);
        action.setActionReason("Low stock");
        action.setActionDescription("Order more milk");
        action.setIsActioned(false);
        action.setCreatedBy("system");
        action.setUpdatedBy("system");
        action.setCreatedAt(Instant.now());
        action.setUpdatedAt(Instant.now());
        action.setItem(item);

        StockAdviceActionMapper mapper = new StockAdviceActionMapper();
        var dto = mapper.mapToResponseDTO(action);

        assertEquals(action.getActionDescription(), dto.getValue());
    }
}
