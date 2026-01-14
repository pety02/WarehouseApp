package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Package;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransferItemMapper {

    public TransferItem toEntity(
            Transfer transfer,
            Item item,
            Integer quantity,
            String user
    ) {
        Instant now = Instant.now();

        return TransferItem.builder()
                .transfer(transfer)
                .item(item)
                .quantity(quantity)
                .createdBy(user)
                .updatedBy(user)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public TransferItemResponseDTO mapToResponseDTO(TransferItem transferItem) {
        if (transferItem == null || transferItem.getItem() == null || transferItem.getTransfer() == null) {
            throw new IllegalArgumentException("TransferItem, Item, and Transfer cannot be null");
        }

        var item = transferItem.getItem();
        var transfer = transferItem.getTransfer();
        var sourceLocation = transfer.getSourceLocation();
        var destinationLocation = transfer.getDestinationLocation();

        return TransferItemResponseDTO.builder()
                .id(transferItem.getId() != null ? transferItem.getId().toString() : null)
                .quantity(transferItem.getQuantity())
                .createdBy(transferItem.getCreatedBy())
                .updatedBy(transferItem.getUpdatedBy())
                .createdAt(transferItem.getCreatedAt() != null ? transferItem.getCreatedAt().toString() : null)
                .updatedAt(transferItem.getUpdatedAt() != null ? transferItem.getUpdatedAt().toString() : null)

                // Item fields
                .itemId(item.getId() != null ? item.getId().toString() : null)
                .itemName(item.getName())
                .itemBarcodeValue(item.getBarcodeValue())

                // Transfer fields
                .transferDate(transfer.getDeliveryDateTime() != null ? transfer.getDeliveryDateTime().toString() : null)
                .transferRemarks(transfer.getRemarks())

                // Source location
                .sourceLocationName(sourceLocation != null ? sourceLocation.getName() : null)
                .sourceLocationAddress(sourceLocation != null ? sourceLocation.getAddress() : null)

                // Destination location
                .destinationLocationName(destinationLocation != null ? destinationLocation.getName() : null)
                .destinationLocationAddress(destinationLocation != null ? destinationLocation.getAddress() : null)
                .build();
    }

    public void updateTransferItem(
            TransferItem existingItem,
            Item item,
            TransferItemUpdateRequestDTO dto,
            String user
    ) {
        if (existingItem == null) {
            throw new IllegalArgumentException("Existing transfer item cannot be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("TransferItemUpdateRequestDTO cannot be null");
        }
        if (user == null || user.isBlank()) {
            throw new IllegalArgumentException("User cannot be null or blank");
        }

        // Update the fields
        existingItem.setQuantity(dto.getQuantity());
        existingItem.setItem(item);
        existingItem.setUpdatedBy(user);
        existingItem.setUpdatedAt(Instant.now());
    }
}
