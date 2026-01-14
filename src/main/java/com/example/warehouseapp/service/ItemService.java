package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.mapper.ItemMapper;
import com.example.warehouseapp.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getAllItemsByLocationId(UUID id) {
        List<Item> itemsList = this.itemRepository.findAllByLocationId(id);
        log.info("======================== ITEMS: " + itemsList.toString());
        return itemsList.stream().map(this.itemMapper::mapToResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public ItemResponseDTO getItemById(UUID id) {
        return this.itemMapper.mapToResponseDTO(this.itemRepository.findItemById(id).orElseThrow());
    }
}
