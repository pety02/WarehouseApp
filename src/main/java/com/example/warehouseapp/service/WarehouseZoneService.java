package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO;
import com.example.warehouseapp.model.mapper.WarehouseZoneMapper;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseZoneService {
    private final WarehouseZoneRepository warehouseZoneRepository;
    private final WarehouseZoneMapper warehouseZoneMapper;

    public List<WarehouseZoneResponseDTO> getAllWarehouseZonesByLocationId(UUID locationId) {
        return this.warehouseZoneRepository.findAllByLocationId(locationId)
                .stream()
                .map(warehouseZoneMapper::mapToResponseDTO)
                .toList();
    }
}
