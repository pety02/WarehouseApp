package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.StockAvailability;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class StockAvailabilityRepositoryTest {

    @Autowired
    private StockAvailabilityRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void getItemById_success() {
        Item item = Item.builder().name("Item").build();
        itemRepository.save(item);

        StockAvailability sa =
                StockAvailability.builder().item(item).piecesCount(10).build();

        repository.save(sa);

        Optional<StockAvailability> result =
                repository.getItemById(sa.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void findAllByLocationId_success() {
        Address address = Address.builder()
                .city("Sofia")
                .country("Bulgaria")
                .street("Main St")
                .no("1")
                .zip("1000")
                .build();

        Location location = Location.builder().name("Loc").address(address).build();

        locationRepository.save(location);

        Item item = Item.builder().name("Item").locations(List.of(location)).build();

        itemRepository.save(item);

        StockAvailability sa =
                StockAvailability.builder().item(item).piecesCount(5).build();

        repository.save(sa);

        List<StockAvailability> result =
                repository.findAllByLocationId(location.getId());

        assertEquals(1, result.size());
    }
}