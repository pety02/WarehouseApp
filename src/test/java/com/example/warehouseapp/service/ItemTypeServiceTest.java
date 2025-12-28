package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.model.entites.ItemType;
import com.example.warehouseapp.model.mapper.ItemTypeMapper;
import com.example.warehouseapp.repository.ItemTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemTypeServiceTest {

    @Mock
    private ItemTypeRepository itemTypeRepository;

    @Mock
    private ItemTypeMapper itemTypeMapper;

    @InjectMocks
    private ItemTypeService itemTypeService;

    @Test
    void getItemTypeById_success() {
        UUID id = UUID.randomUUID();
        ItemType type = ItemType.builder().id(id).name("Food").build();

        when(itemTypeRepository.findById(id)).thenReturn(Optional.of(type));
        when(itemTypeMapper.mapToResponseDTO(type))
                .thenReturn(ItemTypeResponseDTO.builder().id(id.toString()).build());

        ItemTypeResponseDTO dto = itemTypeService.getItemTypeById(id);

        assertEquals(id.toString(), dto.getId());
    }
}
