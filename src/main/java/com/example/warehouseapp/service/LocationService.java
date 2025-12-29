package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.mapper.*;
import com.example.warehouseapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public List<LocationResponseDTO> getAllLocations() {
        List<Location> locationsList = this.locationRepository.findAll();
        return locationsList.stream().map(this.locationMapper::mapToResponseDTO).toList();
    }

    public LocationResponseDTO getLocationById(UUID id) {
        Location location = this.locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Location not found"));

        return this.locationMapper.mapToResponseDTO(location);
    }

    public Location getLocationByAddressAndName(String address, String name) {
        return this.locationRepository.findByAddressAndName(address, name);
    }
}
