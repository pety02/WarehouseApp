package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemUpdateRequestDTO {

    @Size(min = 2, max = 255, message = "Item name must be between 2 and 255 characters")
    private String name;

    @Size(min = 3, max = 100, message = "Barcode value must be between 3 and 100 characters")
    private String barcodeValue;

    private Instant expirationDateTime;

    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be positive")
    private Double sellingPrice;
    private String type;
    private List<String> packages;
    private List<String> currencies;
    private List<String> locations;
}