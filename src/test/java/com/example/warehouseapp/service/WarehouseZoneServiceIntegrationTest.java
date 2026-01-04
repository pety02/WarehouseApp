package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.StorageType;
import com.example.warehouseapp.model.entites.WarehouseZone;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.StorageTypeRepository;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
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
        // create storage type (mandatory)
        StorageType storageType = storageTypeRepository.save(
                StorageType.builder()
                        .name("Standard Storage")
                        .build()
        );

        // create warehouse zone with storage type
        WarehouseZone zone = new WarehouseZone();
        zone.setName("Zone A"); // mandatory
        zone.setStorageType(storageType);
        var savedZone = repository.save(zone);

        // create location and associate zone
        Address address = new Address();
        address.setCity("Sofia");
        address.setCountry("Bulgaria");
        address.setStreet("Main St");
        address.setNo("1");
        address.setZip("1000");

        Location location = new Location();
        location.setName("Warehouse");
        location.setAddress(address);
        location.setWarehouseZones(List.of(savedZone));

        var savedLocation = locationRepository.save(location);

        // call service
        var dtos = service.getAllWarehouseZonesByLocationId(savedLocation.getId());

        // assert
        assertFalse(dtos.isEmpty());
    }
}
