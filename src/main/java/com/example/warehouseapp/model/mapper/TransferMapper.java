package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.dto.TransferUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
public class TransferMapper {

    public TransferResponseDTO mapToResponseDTO(Transfer transfer, List<TransferItemResponseDTO> transferItemResponseDTOList){
        return TransferResponseDTO
                .builder()
                .id(transfer.getId().toString())
                .deliveryDateTime(transfer.getDeliveryDateTime().toString())
                .remarks(transfer.getRemarks())
                .createdBy(transfer.getCreatedBy())
                .updatedBy(transfer.getUpdatedBy())
                .createdAt(transfer.getCreatedAt().toString())
                .updatedAt(transfer.getUpdatedAt().toString())
                .transferResponseDTOList(transferItemResponseDTOList)
                .sourceLocationId(transfer.getSourceLocation().getId().toString())
                .sourceLocationName(transfer.getSourceLocation().getName())
                .sourceLocationAddress(transfer.getSourceLocation().getAddress())
                .destinationLocationId(transfer.getDestinationLocation().getId().toString())
                .destinationLocationName(transfer.getDestinationLocation().getName())
                .destinationLocationAddress(transfer.getDestinationLocation().getAddress())
                .build();
    }

    public Transfer mapToEntity(TransferCreateRequestDTO transferRequestDTO, String user, LocalDate date,
                                List<TransferItem> transferItems, Location sourceLocation, Location destinationLocation) {
        return Transfer
                .builder()
                .deliveryDateTime(transferRequestDTO.getDeliveryDateTime())
                .remarks(transferRequestDTO.getRemarks())
                .createdBy(user)
                .updatedBy(user)
                .createdAt(Instant.from(date))
                .updatedAt(Instant.from(date))
                .transferItems(transferItems)
                .sourceLocation(sourceLocation)
                .destinationLocation(destinationLocation)
                .build();
    }

    public void updateTransfer(Transfer existingTransfer, TransferUpdateRequestDTO transferRequestDTO, String user,
                               LocalDate date, List<TransferItem> transferItems, Location sourceLocation,
                               Location destinationLocation) {
        existingTransfer.setDeliveryDateTime(transferRequestDTO.getDeliveryDateTime());
        existingTransfer.setRemarks(transferRequestDTO.getRemarks());
        existingTransfer.setUpdatedBy(user);
        existingTransfer.setUpdatedAt(Instant.from(date));
        existingTransfer.setTransferItems(transferItems);
        existingTransfer.setSourceLocation(sourceLocation);
        existingTransfer.setDestinationLocation(destinationLocation);
    }
}
