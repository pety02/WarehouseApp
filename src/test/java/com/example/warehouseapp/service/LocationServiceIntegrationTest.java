package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.LocationResponseDTO;
import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.repository.LocationRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class LocationServiceIntegrationTest {

    @Autowired
    private LocationService service;

    @Autowired
    private LocationRepository repository;

    @Disabled
    @Test
    void getAllLocations_realDb() {
        repository.save(Location.builder().name("Sofia").address(new Address()).build());

        List<LocationResponseDTO> locations = service.getAllLocations();

        assertFalse(locations.isEmpty());
    }
}
