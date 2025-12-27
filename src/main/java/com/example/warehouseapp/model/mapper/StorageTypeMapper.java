package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO;
import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO;
import com.example.warehouseapp.model.entites.StorageType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class StorageTypeMapper {

    public StorageTypeResponseDTO mapToResponseDTO(StorageType storageType){
        return StorageTypeResponseDTO
                .builder()
                .id(storageType.getId().toString())
                .name(storageType.getName())
                .build();
    }

    public StorageType mapToEntity(StorageTypeCreateRequestDTO storageTypeCreateRequestDTO, String user, LocalDate date) {
        return StorageType
                .builder()
                .name(storageTypeCreateRequestDTO.getName())
                .createdBy(user)
                .updatedBy(user)
                .createdAt(Instant.from(date))
                .updatedAt(Instant.from(date))
                .build();
    }

    public void updateEntity(StorageType storageType, StorageTypeUpdateRequestDTO storageTypeUpdateRequestDTO,
                                    String user, LocalDate date) {
        storageType.setName(storageTypeUpdateRequestDTO.getName());
        storageType.setUpdatedBy(user);
        storageType.setUpdatedAt(Instant.from(date));
    }
}
