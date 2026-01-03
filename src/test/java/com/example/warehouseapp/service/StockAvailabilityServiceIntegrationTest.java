package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.repository.ItemRepository;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
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

    @Test
    void getAllStockAvailabilitiesByLocationId_realDb() {
        Address address = Address.builder()
                .city("Sofia")
                .country("Bulgaria")
                .street("Main St")
                .no("1")
                .zip("1000")
                .build();

        Location location = locationRepository.save(
                Location.builder()
                        .name("Main Warehouse")
                        .address(address)
                        .build()
        );

        Item item = itemRepository.save(
                Item.builder()
                        .name("Test Item")
                        .barcodeValue("123456")
                        .locations(List.of(location))
                        .build()
        );

        WarehouseZone zone = warehouseZoneRepository.save(
                WarehouseZone.builder()
                        .name("Zone A")
                        .build()
        );

        StockAvailability stock = repository.save(
                StockAvailability.builder()
                        .item(item)
                        .zone(zone)
                        .piecesCount(10)
                        .build()
        );

        List<StockAvailabilityResponseDTO> dtos =
                service.getAllStockAvailabilitiesByLocationId(location.getId());

        assertFalse(dtos.isEmpty(), "Stock availability list should not be empty");
        StockAvailabilityResponseDTO dto = dtos.get(0);

        assertEquals(10, dto.getPiecesCount());
        assertEquals(item.getId().toString(), dto.getItem());
        assertEquals(zone.getId().toString(), dto.getWarehouseZone());
    }
}