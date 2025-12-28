package com.example.warehouseapp.service;

import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockAvailabilityServiceTest {

    @Mock
    private StockAvailabilityRepository repository;
    @Mock
    private StockAvailabilityMapper mapper;

    @InjectMocks
    private StockAvailabilityService service;

    @Test
    void getAllStockAvailabilitiesByLocationId_success() {
        UUID locId = UUID.randomUUID();

        var entity = mock(com.example.warehouseapp.model.entites.StockAvailability.class);
        var dto = mock(com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO.class);

        when(repository.findAllByLocationId(locId)).thenReturn(List.of(entity));
        when(mapper.mapToResponseDTO(entity)).thenReturn(dto);

        List<com.example.warehouseapp.model.dto.StockAvailabilityResponseDTO> result =
                service.getAllStockAvailabilitiesByLocationId(locId);

        assertEquals(1, result.size());
        assertSame(dto, result.get(0));
    }
}
