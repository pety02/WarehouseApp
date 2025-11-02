package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.model.entites.ItemType;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeMapper {

    public ItemTypeResponseDTO mapToResponseDTO(ItemType itemType) {
        return ItemTypeResponseDTO
                .builder()
                .id(itemType.getId().toString())
                .name(itemType.getName())
                .build();
    }
}
