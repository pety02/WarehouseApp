package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Location;
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
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Disabled
    @Test
    void findItemById_success() {
        Item item = itemRepository.save(Item.builder().name("Item1").build());

        Optional<Item> result = itemRepository.findItemById(item.getId());

        assertTrue(result.isPresent());
        assertEquals(item.getId(), result.get().getId());
    }

    @Disabled
    @Test
    void findAllByLocationId_success() {
        Location location = Location.builder().name("Loc").address(new Address()).build();

        Item item = Item.builder()
                .name("Item")
                .locations(List.of(location))
                .build();

        itemRepository.save(item);

        List<Item> items =
                itemRepository.findAllByLocationId(location.getId());

        assertEquals(1, items.size());
    }
}
