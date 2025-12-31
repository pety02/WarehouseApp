package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.WarehouseZone;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.StorageTypeRepository;
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
    @Autowired
    private StorageTypeRepository  storageTypeRepository;

    @Test
    void getAllWarehouseZonesByLocationId_realDb() {
        var location = new com.example.warehouseapp.model.entites.Location();
        location.setAddress(new Address());
        location.setName("Warehouse");
        var storageType = storageTypeRepository.save(new com.example.warehouseapp.model.entites.StorageType());
        var zone = new com.example.warehouseapp.model.entites.WarehouseZone();
        zone.setStorageType(storageType);
        var savedZone = repository.save(zone);
        location.setWarehouseZones(List.of(savedZone));
        var savedLocation = locationRepository.save(location);

        var dtos = service.getAllWarehouseZonesByLocationId(savedLocation.getId());
        assertFalse(dtos.isEmpty());
    }
}
