package com.example.warehouseapp.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LowStockAlertResponseDTO {
    private String id;
    private LocalDate alertDate;
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
