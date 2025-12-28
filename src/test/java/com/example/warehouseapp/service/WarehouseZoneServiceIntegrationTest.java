package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.WarehouseZone;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class WarehouseZoneServiceIntegrationTest {

    @Autowired
    private WarehouseZoneService service;
    @Autowired
    private WarehouseZoneRepository repository;
    @Autowired
    private LocationRepository locationRepository;

    @Test
    void getAllWarehouseZonesByLocationId_realDb() {
        var location = new com.example.warehouseapp.model.entites.Location();
        var zone = repository.save(new com.example.warehouseapp.model.entites.WarehouseZone());
        location.setWarehouseZones(List.of(zone));
        locationRepository.save(location);

        var dtos = service.getAllWarehouseZonesByLocationId(location.getId());
        assertFalse(dtos.isEmpty());
    }
}
