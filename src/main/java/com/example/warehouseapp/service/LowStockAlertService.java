package com.example.warehouseapp.service;

import com.example.warehouseapp.config.schema_exporters.LowStockAlertSchemaExporter;
import com.example.warehouseapp.exception.JsonParseException;
import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.mapper.LowStockAlertMapper;
import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.EmployeeRepository;
import com.example.warehouseapp.repository.LowStockAlertRepository;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LowStockAlertService {
    private final Client client;
    private final LowStockAlertRepository lowStockAlertRepository;
    private final StockAvailabilityRepository  stockAvailabilityRepository;
    private final StockAvailabilityMapper  stockAvailabilityMapper;
    private final LowStockAlertMapper  lowStockAlertMapper;
    private EmployeeRepository employeeRepository;
    private WarehouseZoneRepository warehouseZoneRepository;

    public LowStockAlertResponseDTO getLowStockAlertById(UUID id){
        LowStockAlert lowStockAlert = this.lowStockAlertRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("LowStockAlert not found"));
        StockAvailability stockAvailability = this.stockAvailabilityRepository
                .findById(lowStockAlert.getStockAvailability().getId())
                .orElseThrow(() -> new NotFoundEntityException("StockAvailability not found"));
        StockAvailabilityResponseDTO stockAvailabilityResponseDTO = this.stockAvailabilityMapper
                .mapToResponseDTO(stockAvailability);

        return this.lowStockAlertMapper.mapToResponseDTO(lowStockAlert, stockAvailabilityResponseDTO);
    }

    public List<LowStockAlertResponseDTO> getAllLowStockAlerts() {
        List<LowStockAlert> lowStockAlertList = this.lowStockAlertRepository.findAll();
        return lowStockAlertList.stream().map(
                    lowStockAlert -> {
                        StockAvailability stockAvailability = this.stockAvailabilityRepository
                                .findById(lowStockAlert.getStockAvailability().getId())
                                .orElseThrow(() -> new NotFoundEntityException("StockAvailability not found"));
                        StockAvailabilityResponseDTO stockAvailabilityResponseDTO = this.stockAvailabilityMapper
                                .mapToResponseDTO(stockAvailability);
                        return this.lowStockAlertMapper.mapToResponseDTO(lowStockAlert, stockAvailabilityResponseDTO);
                    })
                .toList();
    }

    public LowStockAlertResponseDTO predictLowStocks (String user) {
        LocalDate today = LocalDate.now();

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(LowStockAlertSchemaExporter.exportSchema())
                .build();

        GenerateContentResponse response = this.client.models.generateContent(
                "gemma-3-1b-it", LowStockAlertSchemaExporter.PROMPT, config); // check if this is the correct name of the model

        String jsonResponse = response.text();
        log.info("JSON Output: " + jsonResponse);

        ObjectMapper mapper = new ObjectMapper();

        try {
            LowStockAlertResponseDTO dto = mapper.readValue(jsonResponse, LowStockAlertResponseDTO.class);
            List<Employee> employees = dto.getEmployees().stream()
                    .map(email -> {
                        return this.employeeRepository.findEmployeeByEmail(email)
                                .orElseThrow(() -> new NotFoundEntityException("Employee not found with email: " + email));
                    })
                    .toList();
            Item item = this.stockAvailabilityRepository.getItemById(
                            UUID.fromString(dto.getStockAvailability().getItem()))
                    .orElseThrow(() -> new NotFoundEntityException("Item not found")).getItem();
            WarehouseZone zone = this.warehouseZoneRepository
                    .findById(UUID.fromString(dto.getStockAvailability().getWarehouseZone()))
                    .orElseThrow(() -> new NotFoundEntityException("WarehouseZone not found"));
            this.lowStockAlertRepository.save(
                    this.lowStockAlertMapper.mapToEntity(
                        dto,
                        this.stockAvailabilityMapper.mapToEntity(dto.getStockAvailability(), item, zone),
                        employees,
                        today
                    )
            );
            log.info("Parsed DTO: {}", dto);

            return dto;
        } catch (Exception e) {
            throw new JsonParseException("Failed to parse JSON response");
        }
    }
}
