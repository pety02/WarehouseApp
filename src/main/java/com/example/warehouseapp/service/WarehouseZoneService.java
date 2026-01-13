package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.mapper.WarehouseZoneMapper;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseZoneService {
    private final WarehouseZoneRepository warehouseZoneRepository;
    private final LocationRepository locationRepository;
    private final WarehouseZoneMapper warehouseZoneMapper;

    public List<WarehouseZoneResponseDTO> getAllWarehouseZonesByLocationId(UUID locationId) {
        Location location = this.locationRepository.findById(locationId).orElse(null);
        if(location == null) {
            return List.of();
        }
        return this.warehouseZoneRepository.findZonesByLocation(location.getId())
                .stream()
                .map(warehouseZoneMapper::mapToResponseDTO)
                .toList();
    }
}
