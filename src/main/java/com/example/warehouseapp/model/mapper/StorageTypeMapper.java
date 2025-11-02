package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.entites.StorageType;
import org.springframework.stereotype.Component;

@Component
public class StorageTypeMapper {

    public StorageTypeResponseDTO mapToResponseDTO(StorageType storageType){
        return StorageTypeResponseDTO
                .builder()
                .id(storageType.getId().toString())
                .name(storageType.getName())
                .build();
    }
}
