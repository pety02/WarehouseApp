package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class WarehouseZoneRepositoryTest {

    @Autowired
    private WarehouseZoneRepository repository;
    @Autowired
    private LocationRepository locationRepository;

    @Test
    void getWarehouseZoneById_success() {
        WarehouseZone zone = repository.save(
                WarehouseZone.builder().name("ZoneA").build()
        );

        Optional<WarehouseZone> result =
                repository.getWarehouseZoneById(zone.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void findAllByLocationId_success() {
        Location location = Location.builder().name("Loc").address("Addr").build();
        locationRepository.save(location);

        WarehouseZone zone =
                WarehouseZone.builder().name("Zone1").build();
        repository.save(zone);

        location.setWarehouseZones(List.of(zone));
        locationRepository.save(location);

        List<WarehouseZone> zones =
                repository.findAllByLocationId(location.getId());

        assertEquals(1, zones.size());
    }
}
