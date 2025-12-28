package com.example.warehouseapp.model.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferCreateRequestDTO {
    private Instant deliveryDateTime;
    private String remarks;
    private List<String> transferItems;
    private String sourceLocation;
    private String destinationLocation;
}
