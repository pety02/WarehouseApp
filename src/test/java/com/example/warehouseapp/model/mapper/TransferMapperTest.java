package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferMapperTest {

    @Test
    void mapToEntity_shouldCreateTransfer() {
        TransferCreateRequestDTO dto = new TransferCreateRequestDTO();
        dto.setRemarks("Urgent");
        dto.setDeliveryDateTime(Instant.now());

        Location source = new Location();
        Location destination = new Location();

        TransferMapper mapper = new TransferMapper();
        Transfer transfer = mapper.mapToEntity(
                dto,
                "user",
                LocalDate.now(),
                List.of(new TransferItem()),
                source,
                destination
        );

        assertEquals("Urgent", transfer.getRemarks());
        assertEquals(source, transfer.getSourceLocation());
        assertEquals(destination, transfer.getDestinationLocation());
    }

    @Test
    void updateTransfer_shouldUpdateFields() {
        Transfer transfer = new Transfer();
        TransferUpdateRequestDTO dto = new TransferUpdateRequestDTO();
        dto.setRemarks("Updated");

        Location source = new Location();
        Location destination = new Location();

        TransferMapper mapper = new TransferMapper();
        mapper.updateTransfer(
                transfer,
                dto,
                "admin",
                LocalDate.now(),
                List.of(),
                source,
                destination
        );

        assertEquals("Updated", transfer.getRemarks());
        assertEquals("admin", transfer.getUpdatedBy());
    }
}
