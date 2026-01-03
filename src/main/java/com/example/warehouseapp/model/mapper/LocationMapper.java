package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.LocationResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationResponseDTO mapToResponseDTO(Location location) {
        Employee manager = location.getManager(); // may be null

        return LocationResponseDTO.builder()
                .id(location.getId().toString())
                .name(location.getName())
                .address(location.getAddress())
                .managerId(manager != null ? manager.getId().toString() : null)
                .managerName(manager != null ? manager.getName() : null)
                .managerSurname(manager != null ? manager.getSurname() : null)
                .managerEmail(manager != null && manager.getCredentials() != null ? manager.getCredentials().getEmail() : null)
                .managerPhoneNumber(manager != null && manager.getCredentials() != null ? manager.getCredentials().getPhoneNumber() : null)
                .build();
    }
}