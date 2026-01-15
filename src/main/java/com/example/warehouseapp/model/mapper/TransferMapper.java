package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TransferMapper {
    private final TransferItemMapper transferItemMapper;

    public Transfer toEntity(
            TransferCreateRequestDTO dto,
            Location source,
            Location destination,
            String user
    ) {
        Instant now = Instant.now();

        return Transfer.builder()
                .deliveryDateTime(dto.getDeliveryDateTime())
                .remarks(dto.getRemarks())
                .sourceLocation(source)
                .destinationLocation(destination)
                .createdBy(user)
                .updatedBy(user)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public TransferResponseDTO toDTO(Transfer entity) {
        return TransferResponseDTO
                .builder()
                .id(entity.getId().toString())
                .deliveryDateTime(entity.getDeliveryDateTime().toString())
                .remarks(entity.getRemarks())
                .createdAt(entity.getCreatedAt().toString())
                .updatedAt(entity.getUpdatedAt().toString())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .transferResponseDTOList(entity
                        .getItems()
                        .stream()
                        .map(
                                this.transferItemMapper::mapToResponseDTO
                        )
                        .toList())
                .sourceLocationId(entity.getSourceLocation().getId().toString())
                .sourceLocationName(entity.getSourceLocation().getName())
                .sourceLocationAddress(entity.getSourceLocation().getAddress())
                .destinationLocationId(entity.getDestinationLocation().getId().toString())
                .destinationLocationName(entity.getDestinationLocation().getName())
                .destinationLocationAddress(entity.getDestinationLocation().getAddress())
                .build();
    }
}
