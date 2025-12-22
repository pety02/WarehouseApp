package com.example.warehouseapp.model.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LowStockAlertResponseDTO {
    private String id;
    private String alertDate;
    private String message;
    private String actualCount;
    private String neededCount;
    private String recommendations;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;

    private LowStockAlertResponseDTO.StockAvailability stockAvailability;

    private List<String> employees;

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StockAvailability {
        private Integer piecesCount;
        private String createdBy;
        private String updatedBy;
        private Integer createdAt;
        private String updatedAt;
        private String item;
        private String warehouseZone;
    }
}
