package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LocationResponseDTO;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationResponseDTO toResponseDTO(Location location) {
        return LocationResponseDTO
                .builder()
                .id(location.getId().toString())
                .name(location.getName())
                .address(location.getAddress())
                .managerId(location.getManager().getId().toString())
                .managerName(location.getManager().getName())
                .managerSurname(location.getManager().getSurname())
                .managerEmail(location.getManager().getCredentials().getEmail())
                .managerPhoneNumber(location.getManager().getCredentials().getPhoneNumber())
                .build();
    }
}