package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class StockAvailabilityServiceIntegrationTest {

    @Autowired private StockAvailabilityService service;
    @Autowired
    private StockAvailabilityRepository repository;

    @Test
    void getAllStockAvailabilitiesByLocationId_realDb() {
        var stock = com.example.warehouseapp.model.entites.StockAvailability.builder().build();
        repository.save(stock);

        List<StockAvailabilityResponseDTO> dtos =
                (List<StockAvailabilityResponseDTO>) stock.getItem().getLocations().stream().map(location -> service
                        .getAllStockAvailabilitiesByLocationId(location.getId()));

        assertFalse(dtos.isEmpty());
    }
}
