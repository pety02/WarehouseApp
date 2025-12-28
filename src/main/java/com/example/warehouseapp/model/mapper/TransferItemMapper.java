package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransferItemMapper {

    public TransferItemResponseDTO mapToResponseDTO(TransferItem transferItem) {
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
                .transferDate(transferItem.getTransfer().getDeliveryDateTime().toString())
                .transferRemarks(transferItem.getTransfer().getRemarks())
                .sourceLocationName(transferItem.getTransfer().getSourceLocation().getName())
                .sourceLocationAddress(transferItem.getTransfer().getSourceLocation().getAddress())
                .destinationLocationName(transferItem.getTransfer().getDestinationLocation().getName())
                .destinationLocationAddress(transferItem.getTransfer().getDestinationLocation().getAddress())
                .build();
    }

    public TransferItem mapToEntity(
            TransferItemCreateRequestDTO dto,
            Transfer transfer,
            Item item,
            String user
    ) {
        Instant now = Instant.now();

        return TransferItem.builder()
                .quantity(dto.getQuantity())
                .item(item)
                .createdBy(user)
                .updatedBy(user)
                .createdAt(now)
                .updatedAt(now)
                .transfer(transfer)
                .build();
    }

    public void updateTransferItem(
            TransferItem existingItem,
            Item item,
            TransferItemUpdateRequestDTO dto,
            String user
    ) {
        existingItem.setQuantity(dto.getQuantity());
        existingItem.setItem(item);
        existingItem.setUpdatedBy(user);
        existingItem.setUpdatedAt(Instant.now());
    }
}
