package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.repository.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class StockAvailabilityServiceIntegrationTest {

    @Autowired
    private StockAvailabilityService service;

    @Autowired
    private StockAvailabilityRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WarehouseZoneRepository  warehouseZoneRepository;

    @Autowired
    private StorageTypeRepository storageTypeRepository;

    @Test
    void getAllStockAvailabilitiesByLocationId_realDb() {
        // Address and Location
        Address address = Address.builder()
                .city("Sofia")
                .country("Bulgaria")
                .street("Main St")
                .no("1")
                .zip("1000")
                .build();

        Location location = Location.builder()
                        .name("Main Warehouse")
                        .address(address)
                        .build();

        // Item
        Item item = itemRepository.save(
                Item.builder()
                        .name("Test Item")
                        .barcodeValue("123456")
                        .build()
        );

        // StorageType
        StorageType storageType = storageTypeRepository.save(
                StorageType.builder()
                        .name("Standard Storage")
                        .build()
        );

        // WarehouseZone (IMPORTANT: link to location)
        WarehouseZone zone = warehouseZoneRepository.save(
                WarehouseZone.builder()
                        .name("Zone A")
                        .storageType(storageType)
                        .build()
        );

        // IMPORTANT: attach zone to location
        location.setWarehouseZones(List.of(zone));
        locationRepository.save(location);

        // StockAvailability
        repository.save(
                StockAvailability.builder()
                        .item(item)
                        .zone(zone)
                        .piecesCount(10)
                        .build()
        );

        // call service
        List<StockAvailabilityResponseDTO> dtos =
                service.getAllStockAvailabilitiesByLocationId(location.getId());

        // assertions
        assertFalse(dtos.isEmpty(), "Stock availability list should not be empty");

        StockAvailabilityResponseDTO dto = dtos.get(0);
        assertEquals(10, dto.getPiecesCount());
        assertEquals(item.getName(), dto.getItem());
        assertEquals(zone.getName(), dto.getWarehouseZone());
    }
}