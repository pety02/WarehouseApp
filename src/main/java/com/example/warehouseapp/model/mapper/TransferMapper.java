package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.entites.Transfer;
import org.springframework.stereotype.Component;
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
}
