package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.dto.TransferUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class TransferMapper {

    public TransferResponseDTO mapToResponseDTO(
            Transfer transfer,
            List<TransferItemResponseDTO> transferItemResponseDTOList
    ) {
        return TransferResponseDTO.builder()
                .id(transfer.getId() != null ? transfer.getId().toString() : null)
                .deliveryDateTime(
                        transfer.getDeliveryDateTime() != null
                                ? transfer.getDeliveryDateTime().toString()
                                : null
                )
                .remarks(transfer.getRemarks())
                .createdBy(transfer.getCreatedBy())
                .updatedBy(transfer.getUpdatedBy())
                .createdAt(
                        transfer.getCreatedAt() != null
                                ? transfer.getCreatedAt().toString()
                                : null
                )
                .updatedAt(
                        transfer.getUpdatedAt() != null
                                ? transfer.getUpdatedAt().toString()
                                : null
                )
                .transferResponseDTOList(transferItemResponseDTOList)

                .sourceLocationId(
                        transfer.getSourceLocation() != null
                                ? transfer.getSourceLocation().getId().toString()
                                : null
                )
                .sourceLocationName(
                        transfer.getSourceLocation() != null
                                ? transfer.getSourceLocation().getName()
                                : null
                )
                .sourceLocationAddress(
                        transfer.getSourceLocation() != null
                                ? transfer.getSourceLocation().getAddress()
                                : null
                )

                .destinationLocationId(
                        transfer.getDestinationLocation() != null
                                ? transfer.getDestinationLocation().getId().toString()
                                : null
                )
                .destinationLocationName(
                        transfer.getDestinationLocation() != null
                                ? transfer.getDestinationLocation().getName()
                                : null
                )
                .destinationLocationAddress(
                        transfer.getDestinationLocation() != null
                                ? transfer.getDestinationLocation().getAddress()
                                : null
                )
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
                .createdAt(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
                .updatedAt(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
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
        existingTransfer.setUpdatedAt(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        existingTransfer.setTransferItems(transferItems);
        existingTransfer.setSourceLocation(sourceLocation);
        existingTransfer.setDestinationLocation(destinationLocation);
    }
}
