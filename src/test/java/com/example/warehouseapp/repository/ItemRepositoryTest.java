package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void findItemById_success() {
        Item item = itemRepository.save(Item.builder().name("Item1").build());

        Optional<Item> result = itemRepository.findItemById(item.getId());

        assertTrue(result.isPresent());
        assertEquals(item.getId(), result.get().getId());
    }

    @Test
    void findAllByLocationId_success() {
        Location location = Location.builder().name("Loc").address("Addr").build();

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
