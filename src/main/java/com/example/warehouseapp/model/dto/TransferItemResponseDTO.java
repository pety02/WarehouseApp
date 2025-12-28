package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferItemResponseDTO {
    private String id;
    private Integer quantity;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private String itemId;
    private String itemName;
    private String itemBarcodeValue;
    private String transferDate;
    private String transferRemarks;
    private String sourceLocationName;
    private String sourceLocationAddress;
    private String destinationLocationName;
    private String destinationLocationAddress;
}
