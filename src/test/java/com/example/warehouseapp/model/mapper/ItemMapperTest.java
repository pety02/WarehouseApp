package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.entites.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    private ItemMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ItemMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapItemCorrectly() {
        UUID itemId = UUID.randomUUID();

        Currency eur = new Currency();
        eur.setName("Euro");

        Package pkg = new Package();
        pkg.setName("Box");
        pkg.setPiecesCount(10);

        ItemType type = new ItemType();
        type.setName("Food");

        Item item = new Item();
        item.setId(itemId);
        item.setName("Chocolate");
        item.setBarcodeValue("123456789");
        item.setExpirationDateTime(Instant.now());
        item.setSellingPrice(2.99);
        item.setCurrencies(List.of(eur));
        item.setPackages(List.of(pkg));
        item.setType(type);

        ItemResponseDTO dto = mapper.mapToResponseDTO(item);

        assertEquals(itemId.toString(), dto.getId());
        assertEquals("Chocolate", dto.getName());
        assertEquals("123456789", dto.getBarcodeValue());
        assertEquals(2.99, dto.getSellingPrice());
        assertEquals(List.of("Euro"), dto.getCurrencies());
        assertEquals(List.of(Pair.of("Box", "10")), dto.getPackages());
        assertEquals("Food", dto.getItemType());
    }
}
