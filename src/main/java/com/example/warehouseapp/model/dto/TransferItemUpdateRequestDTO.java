package com.example.warehouseapp.model.dto;

import lombok.*;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferItemUpdateRequestDTO {

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 1_000_000, message = "Quantity is too large")
    private Integer quantity;

    @NotBlank(message = "Item ID is required")
    @Size(max = 100, message = "Item ID must not exceed 100 characters")
    private String itemId;
}

