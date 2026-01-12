package com.example.warehouseapp.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextDataDTO {

    private UUID itemId;
    private String itemName;
    private List<String> packages;        // Names of packages
    private List<String> currencies;      // Currency codes
    private UUID zoneId;
    private String zoneName;
    private String storageType;
    private Integer piecesCount;

}