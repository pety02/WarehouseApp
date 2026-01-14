package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class TransferCreateRequestDTO {

    @NotNull
    private Instant deliveryDateTime;

    private String remarks;

    @NotNull
    private UUID sourceLocationId;

    @NotNull
    private UUID destinationLocationId;

    @NotEmpty
    private List<TransferItemCreateRequestDTO> items;
}
