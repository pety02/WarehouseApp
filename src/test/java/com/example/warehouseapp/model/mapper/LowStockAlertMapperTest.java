package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.LowStockAlert;
import com.example.warehouseapp.model.entites.StockAvailability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LowStockAlertMapperTest {

    private LowStockAlertMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new LowStockAlertMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapAlertAndEmployeeEmails() {
        UUID alertId = UUID.randomUUID();

        EmployeeCredentials credentials = new EmployeeCredentials();
        credentials.setEmail("employee@warehouse.com");

        Employee employee = new Employee();
        employee.setCredentials(credentials);

        LowStockAlert alert = new LowStockAlert();
        alert.setId(alertId);
        alert.setAlertDate(LocalDate.now());
        alert.setMessage("Low stock detected");
        alert.setActualCount(5);
        alert.setNeededCount(20);
        alert.setRecommendations("Order more");
        alert.setCreatedBy("system");
        alert.setUpdatedBy("system");
        alert.setCreatedAt(Instant.now());
        alert.setUpdatedAt(Instant.now());
        alert.setEmployees(List.of(employee));

        StockAvailabilityResponseDTO stockDTO = StockAvailabilityResponseDTO.builder().build();

        LowStockAlertResponseDTO dto =
                mapper.mapToResponseDTO(alert, stockDTO);

        assertEquals(alertId.toString(), dto.getId());
        assertEquals("Low stock detected", dto.getMessage());
        assertEquals(5, dto.getActualCount());
        assertEquals(20, dto.getNeededCount());
        assertEquals(List.of("employee@warehouse.com"), dto.getEmployees());
        assertEquals(stockDTO, dto.getStockAvailability());
    }

    @Test
    void mapToEntity_shouldMapFromResponseDTOCorrectly() {
        LowStockAlertResponseDTO dto = LowStockAlertResponseDTO.builder()
                .message("Test")
                .actualCount(3)
                .neededCount(10)
                .recommendations("Refill")
                .createdBy("user")
                .updatedBy("user")
                .createdAt(Instant.now().toString())
                .updatedAt(Instant.now().toString())
                .build();

        StockAvailability availability = new StockAvailability();
        List<Employee> employees = List.of(new Employee());

        LowStockAlert entity = mapper.mapToEntity(
                dto,
                availability,
                employees,
                LocalDate.now()
        );

        assertEquals("Test", entity.getMessage());
        assertEquals(3, entity.getActualCount());
        assertEquals(10, entity.getNeededCount());
        assertEquals(availability, entity.getStockAvailability());
        assertEquals(employees, entity.getEmployees());
    }
}
