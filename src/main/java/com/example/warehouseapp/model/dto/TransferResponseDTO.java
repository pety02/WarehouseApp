package com.example.warehouseapp.model.dto;

import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferResponseDTO {
    private String id;
    private String deliveryDateTime;
    private String remarks;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private List<TransferItemResponseDTO> transferResponseDTOList;
    private String sourceLocationId;
    private String sourceLocationName;
    private String sourceLocationAddress;
    private String destinationLocationId;
    private String destinationLocationName;
    private String destinationLocationAddress;
}