package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.mapper.*;
import com.example.warehouseapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public List<LocationResponseDTO> getAllLocations() {
        List<Location> locationsList = this.locationRepository.findAll();
        return locationsList.stream().map(this.locationMapper::mapToResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public LocationResponseDTO getLocationById(UUID id) {
        Location location = this.locationRepository.findByIdWithManager(id)
                .orElseThrow(() -> new NotFoundEntityException("Location not found"));

        return this.locationMapper.mapToResponseDTO(location);
    }

    @Transactional(readOnly = true)
    public Location getLocationByAddressAndName(Address address, String name) {
        return this.locationRepository.findByAddressAndName(address, name);
    }
}
