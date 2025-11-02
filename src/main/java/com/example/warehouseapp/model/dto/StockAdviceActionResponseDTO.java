package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdviceActionResponseDTO {
    private String id;
    private String actionDescription;
    private String actionReason;
    private Boolean isActioned;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private String itemId;
    private String itemBarcodeValue;
    private String itemName;
    private String itemExpirationDateTime;
}