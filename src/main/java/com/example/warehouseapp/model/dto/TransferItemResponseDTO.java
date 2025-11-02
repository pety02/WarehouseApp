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
}