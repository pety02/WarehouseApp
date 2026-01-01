package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.StockAvailability;
import org.junit.jupiter.api.Disabled;
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
@Disabled
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false"
})
class StockAvailabilityRepositoryTest {

    @Autowired
    private StockAvailabilityRepository repository;

    @Disabled
    @Test
    void getItemById_success() {
        Item item = Item.builder().name("Item").build();

        StockAvailability sa =
                StockAvailability.builder().item(item).piecesCount(10).build();

        repository.save(sa);

        Optional<StockAvailability> result =
                repository.getItemById(item.getId());

        assertTrue(result.isPresent());
    }

    @Disabled
    @Test
    void findAllByLocationId_success() {
        Location location = Location.builder().name("Loc").address(new Address()).build();
        Item item = Item.builder().name("Item").locations(List.of(location)).build();

        StockAvailability sa =
                StockAvailability.builder().item(item).piecesCount(5).build();

        repository.save(sa);

        List<StockAvailability> result =
                repository.findAllByLocationId(location.getId());

        assertEquals(1, result.size());
    }
}
