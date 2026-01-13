package com.example.warehouseapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LowStockAlertRequestDTO {
    @JsonProperty("locationId")
    private UUID id;
}