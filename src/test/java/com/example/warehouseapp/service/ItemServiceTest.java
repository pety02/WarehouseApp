package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.mapper.ItemMapper;
import com.example.warehouseapp.repository.ItemRepository;
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
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getItemById_success() {
        UUID id = UUID.randomUUID();
        Item item = Item.builder().id(id).name("Item").build();

        when(itemRepository.findItemById(id)).thenReturn(Optional.of(item));
        when(itemMapper.mapToResponseDTO(item))
                .thenReturn(ItemResponseDTO.builder().id(id.toString()).build());

        ItemResponseDTO dto = itemService.getItemById(id);

        assertEquals(id.toString(), dto.getId());
    }
}
