package com.example.warehouseapp.service;

import com.example.warehouseapp.config.GeminiConfig;
import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.StockAdvice;
import com.example.warehouseapp.model.entites.StockAdviceAction;
import com.example.warehouseapp.model.mapper.StockAdviceMapper;
import com.example.warehouseapp.repository.StockAdviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockAdviceService {

    private final StockAdviceRepository stockAdviceRepository;
    private final StockAdviceMapper stockAdviceMapper;

    public List<StockAdviceResponseDTO> getAllStockAdvices() {
        return stockAdviceRepository.findAll()
                .stream()
                .map(stockAdviceMapper::mapToResponseDTO)
                .toList();
    }

    public StockAdviceResponseDTO getStockAdviceById(UUID id) {
        StockAdvice stockAdvice = stockAdviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockAdvice not found"));

        return stockAdviceMapper.mapToResponseDTO(stockAdvice);
    }

    public StockAdviceResponseDTO createStockAdvice(StockAdviceCreateRequestDTO dto) {
        StockAdvice stockAdvice = new StockAdvice();

        stockAdvice.setValidUntil(Instant.parse(dto.getValidUntil()));
        stockAdvice.setCreatedByModelVersion(GeminiConfig.MODEL_NAME + ":" + GeminiConfig.MODEL_VERSION);
        stockAdvice.setUpdatedByModelVersion(GeminiConfig.MODEL_NAME + ":" + GeminiConfig.MODEL_VERSION);
        stockAdvice.setReasoning(dto.getReasoning());
        stockAdvice.setIsActioned(dto.getIsActioned());
        stockAdvice.setConfidence(dto.getConfidence());
        stockAdvice.setCreatedAt(Instant.now());
        stockAdvice.setUpdatedAt(Instant.now());

        stockAdvice.setActions(mapActions(dto.getActions()));

        return stockAdviceMapper.mapToResponseDTO(
                stockAdviceRepository.save(stockAdvice)
        );
    }

    public StockAdviceResponseDTO updateStockAdvice(UUID id, StockAdviceUpdateRequestDTO dto) {
        StockAdvice stockAdvice = stockAdviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockAdvice not found"));

        stockAdvice.setValidUntil(Instant.parse(dto.getValidUntil()));
        stockAdvice.setReasoning(dto.getReasoning());
        stockAdvice.setIsActioned(dto.getIsActioned());
        stockAdvice.setConfidence(dto.getConfidence());
        stockAdvice.setUpdatedAt(Instant.now());

        stockAdvice.setActions(mapActions(dto.getActions()));

        return stockAdviceMapper.mapToResponseDTO(
                stockAdviceRepository.save(stockAdvice)
        );
    }

    public void deleteStockAdvice(UUID id) {
        if (!stockAdviceRepository.existsById(id)) {
            throw new EntityNotFoundException("StockAdvice not found");
        }
        stockAdviceRepository.deleteById(id);
    }

    private List<StockAdviceAction> mapActions(Map<String, String> actions) {
        if (actions == null) return List.of();

        return actions.entrySet()
                .stream()
                .map(entry -> {
                    StockAdviceAction action = new StockAdviceAction();
                    action.setActionDescription(entry.getValue());
                    return action;
                })
                .toList();
    }
}
