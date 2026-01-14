package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Data
public class TransferUpdateRequestDTO {

    @NotNull
    private Instant deliveryDateTime;

    private String remarks;
}