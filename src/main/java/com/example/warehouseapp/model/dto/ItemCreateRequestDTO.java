package com.example.warehouseapp.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequestDTO {
    private String name;
    private String barcodeValue;
    private Instant expirationDateTime;
    private Double sellingPrice;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<String> packages;
    private Set<String> currencies;
    private String type;
    private List<String> locations;
}
