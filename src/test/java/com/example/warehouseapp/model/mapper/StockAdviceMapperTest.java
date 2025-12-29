package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAdviceResponseDTO;
import com.example.warehouseapp.model.entites.StockAdvice;
import com.example.warehouseapp.model.entites.StockAdviceAction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StockAdviceMapperTest {

    private final StockAdviceActionMapper actionMapper = Mockito.mock(StockAdviceActionMapper.class);
    private final StockAdviceMapper mapper = new StockAdviceMapper(actionMapper);

    @Test
    void mapToResponseDTO_success() {
        StockAdviceAction action = new StockAdviceAction();
        Mockito.when(actionMapper.mapToResponseDTO(action))
                .thenReturn(Map.entry("1", "Buy more"));

        StockAdvice entity = new StockAdvice();
        entity.setId(UUID.randomUUID());
        entity.setValidUntil(Instant.now());
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());
        entity.setConfidence(0.8);
        entity.setIsActioned(false);
        entity.setActions(List.of(action));

        StockAdviceResponseDTO dto = mapper.mapToResponseDTO(entity);

        assertThat(dto.getActions()).containsEntry("1", "Buy more");
        assertThat(dto.getConfidence()).isEqualTo(0.8);
    }
}
