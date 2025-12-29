package com.example.warehouseapp.model.dto;

import lombok.*;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdviceResponseDTO {
    private String id;
    private String validUntil;
    private String reasoning;
    private Boolean isActioned;
    private Double confidence;
    private String createdByModelVersion;
    private String updatedByModelVersion;
    private String createdAt;
    private String updatedAt;
    private Map<String, String> actions; // Map of action ID to action description
}
