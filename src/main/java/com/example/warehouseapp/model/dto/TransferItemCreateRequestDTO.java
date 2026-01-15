package com.example.warehouseapp.model.dto;

import lombok.*;

import jakarta.validation.constraints.*;

import java.util.UUID;

@Data
public class TransferItemCreateRequestDTO {

    @NotNull
    private UUID itemId;

    @Min(1)
    private Integer quantity;
}
