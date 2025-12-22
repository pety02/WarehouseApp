package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.LowStockAlert;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LowStockAlertMapper {

    public LowStockAlertResponseDTO mapToResponseDTO(LowStockAlert lowStockAlert,
                                                     LowStockAlertResponseDTO.StockAvailability stockAvailabilityResponseDTO) {
        return LowStockAlertResponseDTO
                .builder()
                .id(lowStockAlert.getId().toString())
                .alertDate(lowStockAlert.getAlertDate().toString())
                .message(lowStockAlert.getMessage())
                .actualCount(lowStockAlert.getActualCount().toString())
                .neededCount(lowStockAlert.getNeededCount().toString())
                .recommendations(lowStockAlert.getRecommendations())
                .createdBy(lowStockAlert.getCreatedBy())
                .updatedBy(lowStockAlert.getUpdatedBy())
                .createdAt(lowStockAlert.getCreatedAt().toString())
                .updatedAt(lowStockAlert.getUpdatedAt().toString())
                .stockAvailability(stockAvailabilityResponseDTO)
                .employees(lowStockAlert
                        .getEmployees()
                        .stream()
                        .map(emp -> emp
                                .getCredentials()
                                .getEmail()) // to send emails when low stock alert is predicted from Gemini model
                        .toList())
                .build();
    }

    public LowStockAlert mapToEntity(LowStockAlertResponseDTO lowStockAlertRequestDTO, String user, LocalDate date) {
        // TODO: Implement mapping from LowStockAlertCreateRequestDTO to LowStockAlert entity
        return null;
    }
}
