package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.StorageType;
import com.example.warehouseapp.model.mapper.StorageTypeMapper;
import com.example.warehouseapp.repository.StorageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageTypeService {
    private final StorageTypeRepository storageTypeRepository;
    private final StorageTypeMapper storageTypeMapper;

    public List<StorageTypeResponseDTO> getAllStorageTypes() {
        List<StorageType> storageTypesList = this.storageTypeRepository.findAll();
        return storageTypesList.stream().map(this.storageTypeMapper::mapToResponseDTO).toList();
    }

    public StorageTypeResponseDTO createStorageType(StorageTypeCreateRequestDTO storageTypeRequestDTO, String user) {
        LocalDate today = LocalDate.now();
        StorageType storageType = this.storageTypeMapper.mapToEntity(storageTypeRequestDTO, user, today);
        StorageType savedStorageType = this.storageTypeRepository.save(storageType);
        return this.storageTypeMapper.mapToResponseDTO(savedStorageType);
    }

    public StorageTypeResponseDTO updateStorageTypeById(UUID id, StorageTypeUpdateRequestDTO storageTypeRequestDTO, String user) {
        LocalDate today = LocalDate.now();
        StorageType existingStorageType = this.storageTypeRepository.findById(id).orElse(null);
        if (existingStorageType == null) {
            throw new IllegalArgumentException("StorageType with id " + id + " not found.");
        }

        this.storageTypeMapper.updateEntity(existingStorageType, storageTypeRequestDTO, user, today);
        return this.storageTypeMapper.mapToResponseDTO(this.storageTypeRepository.save(existingStorageType));
    }

    public void deleteStorageTypeById(UUID id) {
        this.storageTypeRepository.deleteById(id);
    }
}
