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
        var transfer = transferItem.getTransfer();
        var item = transferItem.getItem();

        var sourceLocation = transfer != null ? transfer.getSourceLocation() : null;
        var destinationLocation = transfer != null ? transfer.getDestinationLocation() : null;

        return TransferItemResponseDTO.builder()
                .id(transferItem.getId() != null ? transferItem.getId().toString() : null)
                .quantity(transferItem.getQuantity())
                .createdBy(transferItem.getCreatedBy())
                .updatedBy(transferItem.getUpdatedBy())
                .createdAt(transferItem.getCreatedAt() != null ? transferItem.getCreatedAt().toString() : null)
                .updatedAt(transferItem.getUpdatedAt() != null ? transferItem.getUpdatedAt().toString() : null)

                .itemId(item != null && item.getId() != null ? item.getId().toString() : null)
                .itemName(item != null ? item.getName() : null)
                .itemBarcodeValue(item != null ? item.getBarcodeValue() : null)

                .transferDate(
                        transfer != null && transfer.getDeliveryDateTime() != null
                                ? transfer.getDeliveryDateTime().toString()
                                : null
                )
                .transferRemarks(transfer != null ? transfer.getRemarks() : null)

                .sourceLocationName(sourceLocation != null ? sourceLocation.getName() : null)
                .sourceLocationAddress(sourceLocation != null ? sourceLocation.getAddress() : null)

                .destinationLocationName(destinationLocation != null ? destinationLocation.getName() : null)
                .destinationLocationAddress(destinationLocation != null ? destinationLocation.getAddress() : null)

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
