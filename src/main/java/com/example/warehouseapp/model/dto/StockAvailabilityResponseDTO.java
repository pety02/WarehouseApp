package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockAvailabilityResponseDTO {
    private String id;
    private Integer piecesCount;
    private String itemId;
    private String itemName;
    private String itemBarcodeValue;
    private String itemExpirationDateTime;
    private String zoneId;
    private String zoneName;
    private String zoneStorageType;
}
