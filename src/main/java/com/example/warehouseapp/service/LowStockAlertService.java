package com.example.warehouseapp.service;

import com.example.warehouseapp.config.schema_exporters.LowStockAlertSchemaExporter;
import com.example.warehouseapp.exception.JsonParseException;
import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.ContextDataDTO;
import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.entites.Package;
import com.example.warehouseapp.model.mapper.LowStockAlertMapper;
import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.*;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.ThinkingConfig;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LowStockAlertService {

    private final Client client;
    private final LowStockAlertRepository lowStockAlertRepository;
    private final EmployeeRepository employeeRepository;
    private final StockAvailabilityRepository stockAvailabilityRepository;
    private final WarehouseZoneRepository warehouseZoneRepository;
    private final LowStockAlertMapper lowStockAlertMapper;
    private final StockAvailabilityMapper stockAvailabilityMapper;
    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;
    private final TransferItemRepository transferItemRepository;
    private final TransferRepository transferRepository;

    @Transactional(readOnly = true)
    public List<ContextDataDTO> buildContextData(UUID locationId) {
        // Fetch stock availabilities (Item and zone are fetch-joined)
        List<StockAvailability> stockAvailabilities =
                stockAvailabilityRepository.findAllByLocationIdWithItems(locationId);

        // Map to DTO safely inside transaction
        return stockAvailabilities.stream().map(sa -> {
            Item item = sa.getItem();

            WarehouseZone zone = sa.getZone();
            Hibernate.initialize(zone.getStorageType());

            return ContextDataDTO.builder()
                    .itemId(item.getId())
                    .itemName(item.getName())
                    .packages(item.getPackages().stream()
                            .map(Package::getName) // adjust field
                            .toList())
                    .currencies(item.getCurrencies().stream()
                            .map(Currency::getAbbreviation)
                            .toList())
                    .zoneId(zone.getId())
                    .zoneName(zone.getName())
                    .storageType(zone.getStorageType().getName())
                    .piecesCount(sa.getPiecesCount())
                    .build();
        }).toList();
    }

    public LowStockAlertResponseDTO predictLowStocks(String username, UUID locationId) {
        LocalDate today = LocalDate.now();

        ThinkingConfig thinking = ThinkingConfig.builder()
                .includeThoughts(true)
                .thinkingBudget(500)
                .build();

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(LowStockAlertSchemaExporter.exportSchema())
                .thinkingConfig(thinking)
                .build();

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash", LowStockAlertSchemaExporter.PROMPT + " "
                        + buildContextData(locationId), config);

        String jsonResponse = response.text();
        log.info("JSON Output: " + jsonResponse);

        try {
            LowStockAlertResponseDTO dto = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(jsonResponse, LowStockAlertResponseDTO.class);

            /*List<Employee> employees = dto.getEmployees().stream()
                    .map(email -> employeeRepository.findEmployeeByEmail(email)
                            .orElseThrow(() -> new NotFoundEntityException(
                                    "Employee not found with email: " + email)))
                    .collect(Collectors.toList());

            StockAvailability stockAvailability = stockAvailabilityRepository
                    .getItemById(UUID.fromString(dto.getStockAvailability().getItem()))
                    .orElseThrow(() -> new NotFoundEntityException("Item not found"));

            WarehouseZone zone = warehouseZoneRepository.findById(
                            UUID.fromString(dto.getStockAvailability().getWarehouseZone()))
                    .orElseThrow(() -> new NotFoundEntityException("WarehouseZone not found"));

            lowStockAlertRepository.save(
                    lowStockAlertMapper.mapToEntity(dto, stockAvailability, employees, today)
            );*/

            return dto;

        } catch (Exception e) {
            throw new JsonParseException("Failed to parse JSON response from Gemini");
        }
    }

    public LowStockAlertResponseDTO getLowStockAlertById(UUID id) {
        return lowStockAlertRepository.findById(id)
                .map((LowStockAlert lowStockAlert) -> lowStockAlertMapper.mapToResponseDTO(lowStockAlert,
                        stockAvailabilityMapper.mapToResponseDTO(lowStockAlert.getStockAvailability())))
                .orElseThrow(() -> new EntityNotFoundException("LowStockAlert not found with id: " + id));
    }

    public List<LowStockAlertResponseDTO> getAllLowStockAlerts() {
        return lowStockAlertRepository.findAll()
                .stream()
                .map((LowStockAlert lowStockAlert) -> lowStockAlertMapper.mapToResponseDTO(lowStockAlert,
                                stockAvailabilityMapper.mapToResponseDTO(lowStockAlert.getStockAvailability())))
                .collect(Collectors.toList());
    }
}
