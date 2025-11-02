package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.entites.StorageType;
import com.example.warehouseapp.model.mapper.StorageTypeMapper;
import com.example.warehouseapp.repository.StorageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageTypeService {
    private final StorageTypeRepository storageTypeRepository;
    private final StorageTypeMapper storageTypeMapper;

    public List<StorageTypeResponseDTO> getAllStorageTypes() {
        List<StorageType> storageTypesList = this.storageTypeRepository.findAll();
        return storageTypesList.stream().map(this.storageTypeMapper::mapToResponseDTO).toList();
    }
}
