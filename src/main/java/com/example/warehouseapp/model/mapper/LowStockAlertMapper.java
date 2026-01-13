package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.LowStockAlert;
import com.example.warehouseapp.model.entites.StockAvailability;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

import java.util.List;

@Component
public class LowStockAlertMapper {

    public LowStockAlertResponseDTO mapToResponseDTO(LowStockAlert lowStockAlert,
                                                     StockAvailabilityResponseDTO stockAvailabilityResponseDTO) {
        return LowStockAlertResponseDTO
                .builder()
                .alertDate(lowStockAlert.getAlertDate().toString())
                .message(lowStockAlert.getMessage())
                .actualCount(lowStockAlert.getActualCount())
                .neededCount(lowStockAlert.getNeededCount())
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

    public LowStockAlert mapToEntity(LowStockAlertResponseDTO lowStockAlertRequestDTO, StockAvailability availability, List<Employee> employees, LocalDate date) {

        return LowStockAlert.builder()
                .alertDate(date)
                .message(lowStockAlertRequestDTO.getMessage())
                .actualCount(lowStockAlertRequestDTO.getActualCount())
                .neededCount(lowStockAlertRequestDTO.getNeededCount())
                .recommendations(lowStockAlertRequestDTO.getRecommendations())
                .createdBy(lowStockAlertRequestDTO.getCreatedBy())
                .updatedBy(lowStockAlertRequestDTO.getUpdatedBy())
                .createdAt(Instant.parse(lowStockAlertRequestDTO.getCreatedAt()))
                .updatedAt(Instant.parse(lowStockAlertRequestDTO.getUpdatedAt()))
                .stockAvailability(availability)
                .employees(employees)
                .build();
    }
}
