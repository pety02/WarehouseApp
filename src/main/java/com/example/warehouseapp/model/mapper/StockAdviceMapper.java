package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAdviceResponseDTO;
import com.example.warehouseapp.model.entites.StockAdvice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockAdviceMapper {
    private final StockAdviceActionMapper stockAdviceActionMapper;

    public StockAdviceResponseDTO mapToResponseDTO(StockAdvice stockAdvice) {
        return StockAdviceResponseDTO
                .builder()
                .id(stockAdvice.getId().toString())
                .validUntil(stockAdvice.getValidUntil().toString())
                .reasoning(stockAdvice.getReasoning())
                .isActioned(stockAdvice.getIsActioned())
                .confidence(stockAdvice.getConfidence())
                .createdByModelVersion(stockAdvice.getCreatedByModelVersion())
                .updatedByModelVersion(stockAdvice.getUpdatedByModelVersion())
                .createdAt(stockAdvice.getCreatedAt().toString())
                .updatedAt(stockAdvice.getUpdatedAt().toString())
                .actions(stockAdvice.getActions()
                        .stream()
                        .map(this.stockAdviceActionMapper::mapToResponseDTO)
                        .toList()
                )
                .build();
    }
}
