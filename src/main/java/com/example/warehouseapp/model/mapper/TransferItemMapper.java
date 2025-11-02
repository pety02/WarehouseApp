package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.stereotype.Component;

@Component
public class TransferItemMapper {

    public TransferItemResponseDTO mapToResponseDTO(TransferItem transferItem){
        return TransferItemResponseDTO
                .builder()
                .id(transferItem.getId().toString())
                .quantity(transferItem.getQuantity())
                .createdBy(transferItem.getCreatedBy())
                .updatedBy(transferItem.getUpdatedBy())
                .createdAt(transferItem.getCreatedAt().toString())
                .updatedAt(transferItem.getUpdatedAt().toString())
                .itemId(transferItem.getItem().getId().toString())
                .itemName(transferItem.getItem().getName())
                .itemBarcodeValue(transferItem.getItem().getBarcodeValue())
                .build();
    }
}
