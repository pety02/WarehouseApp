package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAdviceActionResponseDTO;
import com.example.warehouseapp.model.entites.StockAdviceAction;
import org.springframework.stereotype.Component;

@Component
public class StockAdviceActionMapper {

    public StockAdviceActionResponseDTO mapToResponseDTO(StockAdviceAction stockAdviceAction) {
        return StockAdviceActionResponseDTO
                .builder()
                .id(stockAdviceAction.getId().toString())
                .actionReason(stockAdviceAction.getActionReason())
                .actionDescription(stockAdviceAction.getActionDescription())
                .isActioned(stockAdviceAction.getIsActioned())
                .createdBy(stockAdviceAction.getCreatedBy())
                .updatedBy(stockAdviceAction.getUpdatedBy())
                .createdAt(stockAdviceAction.getCreatedAt().toString())
                .updatedAt(stockAdviceAction.getUpdatedAt().toString())
                .itemId(stockAdviceAction.getItem().getId().toString())
                .itemName(stockAdviceAction.getItem().getName())
                .itemBarcodeValue(stockAdviceAction.getItem().getBarcodeValue())
                .itemExpirationDateTime(stockAdviceAction.getItem().getExpirationDateTime().toString())
                .build();
    }
}