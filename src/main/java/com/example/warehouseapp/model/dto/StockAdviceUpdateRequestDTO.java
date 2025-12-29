package com.example.warehouseapp.model.dto;

import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdviceUpdateRequestDTO {
    private String validUntil;
    private String reasoning;
    private Boolean isActioned;
    private Double confidence;
    private Map<String, String> actions;
}
