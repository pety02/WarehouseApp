package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAdviceResponseDTO;
import com.example.warehouseapp.model.entites.StockAdvice;
import com.example.warehouseapp.model.entites.StockAdviceAction;
import com.example.warehouseapp.model.entites.Item;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StockAdviceMapperTest {

    @Test
    void mapToResponseDTO_shouldMapAdviceAndActions() {
        StockAdviceActionMapper actionMapper = new StockAdviceActionMapper();
        StockAdviceMapper mapper = new StockAdviceMapper(actionMapper);

        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName("Milk");

        StockAdviceAction action = new StockAdviceAction();
        action.setId(UUID.randomUUID());
        action.setActionReason("Low stock");
        action.setItem(item);
        action.setCreatedAt(Instant.now());
        action.setUpdatedAt(Instant.now());

        StockAdvice advice = new StockAdvice();
        advice.setId(UUID.randomUUID());
        advice.setValidUntil(Instant.from(LocalDate.now()));
        advice.setReasoning("Demand increase");
        advice.setIsActioned(false);
        advice.setConfidence(0.85);
        advice.setCreatedByModelVersion("v1");
        advice.setUpdatedByModelVersion("v1");
        advice.setCreatedAt(Instant.now());
        advice.setUpdatedAt(Instant.now());
        advice.setActions(List.of(action));

        StockAdviceResponseDTO dto = mapper.mapToResponseDTO(advice);

        assertEquals(advice.getId().toString(), dto.getId());
        assertEquals("Demand increase", dto.getReasoning());
        assertFalse(dto.getIsActioned());
        assertEquals(1, dto.getActions().size());
    }
}
