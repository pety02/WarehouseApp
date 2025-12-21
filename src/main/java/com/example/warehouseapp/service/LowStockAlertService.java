package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.LowStockAlert;
import com.example.warehouseapp.model.entites.StockAvailability;
import com.example.warehouseapp.model.mapper.LowStockAlertMapper;
import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.LowStockAlertRepository;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import com.google.genai.Client;
import com.google.genai.ResponseSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LowStockAlertService {
    private final Client client;
    private final LowStockAlertRepository lowStockAlertRepository;
    private final StockAvailabilityRepository  stockAvailabilityRepository;
    private final StockAvailabilityMapper  stockAvailabilityMapper;
    private final LowStockAlertMapper  lowStockAlertMapper;

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

    public void generatePrediction() {
        String prompt = "Predict warehouse stock levels for product ABC tomorrow.";

        // Define the JSON schema for structured output
        ResponseSchema schema = ResponseSchema.builder()
                .addProperty("date", ResponseSchema.Type.STRING)
                .addProperty("predicted_stock", ResponseSchema.Type.INT)
                .addProperty("notes", ResponseSchema.Type.STRING)
                .build();

        // Generate structured response
        String jsonResponse = client.models()
                .generateStructured("gemma-3", prompt, schema);

        // Optional: parse JSON into Java object
        System.out.println("Generated prediction JSON: " + jsonResponse);
    }
}
