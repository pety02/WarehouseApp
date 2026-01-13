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
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LowStockAlertService {

    private final Client client;
    private final LowStockAlertRepository lowStockAlertRepository;
    private final StockAvailabilityRepository stockAvailabilityRepository;
    private final LowStockAlertMapper lowStockAlertMapper;
    private final StockAvailabilityMapper stockAvailabilityMapper;
    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<ContextDataDTO> buildContextData(UUID locationId) {
        List<StockAvailability> stockAvailabilities =
                stockAvailabilityRepository.findAllByLocationIdWithItems(locationId);

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

    private GenerateContentConfig getContentConfig() {
        ThinkingConfig thinking = ThinkingConfig.builder()
                .includeThoughts(true)
                .thinkingBudget(500)
                .build();

        return GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(LowStockAlertSchemaExporter.exportSchema())
                .thinkingConfig(thinking)
                .build();
    }

    @Transactional(readOnly = true)
    public String generateData(UUID locationId, String extraData) {
        String contextData = LowStockAlertSchemaExporter.PROMPT + " "
                + buildContextData(locationId);
        if (extraData != null && !extraData.isBlank()) {
            contextData += extraData;
        }
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash", contextData, getContentConfig());

        String jsonResponse = response.text();
        log.info("JSON Output: " + jsonResponse);

        return jsonResponse;
    }

    @Transactional
    public LowStockAlertResponseDTO saveAlert(UUID locationId, String jsonResponse) throws JsonProcessingException {
        LowStockAlertResponseDTO dto = new com.fasterxml.jackson.databind.ObjectMapper()
                .readValue(jsonResponse, LowStockAlertResponseDTO.class);

        Location currentLocation = this.locationRepository.findById(locationId).orElseThrow(() ->
                new EntityNotFoundException("Location cannot be found"));
        Employee locationManager = currentLocation.getManager();
        if(locationManager == null) {
            throw new EntityNotFoundException("Location Manager cannot be found");
        }
        dto.setAlertDate(Instant.now().toString());
        dto.setEmployees(List.of(locationManager.getName() + " " + locationManager.getSurname()));
        dto.setCreatedBy("system");
        dto.setUpdatedBy("system");
        dto.setCreatedAt(Instant.now().toString());
        dto.setUpdatedAt(Instant.now().toString());

        StockAvailability stockAvailability = stockAvailabilityRepository
                .getItemByLocationIdAndItemName(locationId, dto.getStockAvailability().getItem())
                .orElseThrow(() -> new NotFoundEntityException("Item not found"));

        lowStockAlertRepository.save(
                lowStockAlertMapper.mapToEntity(dto, stockAvailability, List.of(locationManager), LocalDate.now())
        );
        lowStockAlertRepository.flush();

        return dto;
    }

    @Transactional
    public LowStockAlertResponseDTO constructAndSaveAlert(UUID locationId) throws JsonProcessingException {
        String jsonResponse = generateData(locationId, null);
        return saveAlert(locationId, jsonResponse);
    }

    @Transactional
    public LowStockAlertResponseDTO predictLowStocks(String username, UUID locationId) {
        try {
            return constructAndSaveAlert(locationId);
        } catch (Exception outerException) {
            try {
                String lowStockAlertWithAdditionalInstructions = generateData(locationId,
                        " For recommendations use only data provided here from the " +
                                "database of this application.");
                return saveAlert(locationId, lowStockAlertWithAdditionalInstructions);
            } catch (JsonProcessingException innerException) {
                throw new JsonParseException("Failed to parse JSON response from Gemini for two times");
            }
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