package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.model.entites.ItemType;
import com.example.warehouseapp.model.mapper.ItemTypeMapper;
import com.example.warehouseapp.repository.ItemTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemTypeService {
    private final ItemTypeRepository itemTypeRepository;
    private final ItemTypeMapper itemTypeMapper;

    public ItemTypeResponseDTO getItemTypeById(UUID id) {
        ItemType itemType = this.itemTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("ItemType not found"));

        return this.itemTypeMapper.mapToResponseDTO(itemType);
    }

    public List<ItemTypeResponseDTO> getAllItemTypes() {
        List<ItemType> itemTypesList = this.itemTypeRepository.findAll();
        return itemTypesList.stream().map(this.itemTypeMapper::mapToResponseDTO).toList();
    }
}
