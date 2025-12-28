package com.example.warehouseapp.service;

import com.example.warehouseapp.model.mapper.StorageTypeMapper;
import com.example.warehouseapp.repository.StorageTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageTypeServiceTest {

    @Mock
    private StorageTypeRepository repository;
    @Mock
    private StorageTypeMapper mapper;

    @InjectMocks
    private StorageTypeService service;

    @Test
    void getAllStorageTypes_returnsMappedList() {
        var entity = mock(com.example.warehouseapp.model.entites.StorageType.class);
        var dto = mock(com.example.warehouseapp.model.dto.StorageTypeResponseDTO.class);

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.mapToResponseDTO(entity)).thenReturn(dto);

        List<com.example.warehouseapp.model.dto.StorageTypeResponseDTO> result = service.getAllStorageTypes();
        assertEquals(1, result.size());
        assertSame(dto, result.get(0));
    }

    @Test
    void updateStorageTypeById_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> service.updateStorageTypeById(id,
                        new com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO(),
                        "user"));
    }
}
