package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.LowStockAlert;
import org.springframework.stereotype.Component;

@Component
public class LowStockAlertMapper {

    public LowStockAlertResponseDTO mapToResponseDTO(LowStockAlert lowStockAlert,
                                                     StockAvailabilityResponseDTO stockAvailabilityResponseDTO) {
        return LowStockAlertResponseDTO
                .builder()
                .id(lowStockAlert.getId().toString())
                .alertDate(lowStockAlert.getAlertDate().toString())
                .message(lowStockAlert.getMessage())
                .actualCount(lowStockAlert.getActualCount())
                .neededCount(lowStockAlert.getNeededCount())
                .recommendations(lowStockAlert.getRecommendations())
                .createdBy(lowStockAlert.getCreatedBy())
                .updatedBy(lowStockAlert.getUpdatedBy())
                .createdAt(lowStockAlert.getCreatedAt().toString())
                .updatedAt(lowStockAlert.getUpdatedAt().toString())
                .availability(stockAvailabilityResponseDTO)
                .employeesEmails(lowStockAlert
                        .getEmployees()
                        .stream()
                        .map(emp -> emp
                                .getCredentials()
                                .getEmail())
                        .toList())
                .build();
    }
}