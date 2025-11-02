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
    private Integer actualCount;
    private Integer neededCount;
    private String recommendations;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private StockAvailabilityResponseDTO availability;
    private List<String> employeesEmails;
}
