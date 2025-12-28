package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.mapper.ItemMapper;
import com.example.warehouseapp.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public List<ItemResponseDTO> getAllItemsByLocationId(UUID id) {
        List<Item> itemsList = this.itemRepository.findAllByLocationId(id);
        return itemsList.stream().map(this.itemMapper::mapToResponseDTO).toList();
    }

    public ItemResponseDTO getItemById(UUID id) {
        return this.itemMapper.mapToResponseDTO(this.itemRepository.findItemById(id).orElseThrow());
    }
}
