package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.StockAvailability;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class StockAvailabilityMapperTest {

    private final StockAvailabilityMapper mapper = new StockAvailabilityMapper();

    @Test
    void mapToResponseDTO_success() {
        Item item = new Item();
        item.setId(UUID.randomUUID());

        WarehouseZone zone = new WarehouseZone();
        zone.setId(UUID.randomUUID());

        StockAvailability entity = StockAvailability.builder()
                .piecesCount(10)
                .createdBy("system")
                .updatedBy("system")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .item(item)
                .zone(zone)
                .build();

        StockAvailabilityResponseDTO dto = mapper.mapToResponseDTO(entity);

        assertThat(dto.getPiecesCount()).isEqualTo(10);
        assertThat(dto.getItem()).isEqualTo(item.getId().toString());
    }
}
