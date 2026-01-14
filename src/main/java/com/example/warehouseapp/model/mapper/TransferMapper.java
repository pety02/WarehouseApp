package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransferMapper {

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
}
