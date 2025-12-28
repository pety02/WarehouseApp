package com.example.warehouseapp.service;

import com.example.warehouseapp.model.mapper.WarehouseZoneMapper;
import com.example.warehouseapp.repository.WarehouseZoneRepository;
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
class WarehouseZoneServiceTest {

    @Mock
    private WarehouseZoneRepository repository;
    @Mock
    private WarehouseZoneMapper mapper;

    @InjectMocks
    private WarehouseZoneService service;

    @Test
    void getAllWarehouseZonesByLocationId_success() {
        UUID locId = UUID.randomUUID();
        var entity = mock(com.example.warehouseapp.model.entites.WarehouseZone.class);
        var dto = mock(com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO.class);

        when(repository.findAllByLocationId(locId)).thenReturn(List.of(entity));
        when(mapper.mapToResponseDTO(entity)).thenReturn(dto);

        List<com.example.warehouseapp.model.dto.WarehouseZoneResponseDTO> result =
                service.getAllWarehouseZonesByLocationId(locId);

        assertEquals(1, result.size());
        assertSame(dto, result.get(0));
    }
}
