package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockAvailabilityService {
    private final StockAvailabilityRepository stockAvailabilityRepository;
    private final StockAvailabilityMapper stockAvailabilityMapper;
    public List<StockAvailabilityResponseDTO> getAllStockAvailabilitiesByLocationId(UUID locationId) {
        return this.stockAvailabilityRepository.findAllByLocationId(locationId)
                .stream()
                .map(stockAvailabilityMapper::mapToResponseDTO)
                .toList();
    }
}
