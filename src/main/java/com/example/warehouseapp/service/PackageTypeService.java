package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.entites.PackageType;
import com.example.warehouseapp.model.mapper.PackageTypeMapper;
import com.example.warehouseapp.repository.PackageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackageTypeService {
    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;

    public PackageTypeResponseDTO getItemTypeById(UUID id) {
        PackageType packageType = this.packageTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("PackageType not found"));

        return this.packageTypeMapper.mapToPackageType(packageType);
    }

    public List<PackageTypeResponseDTO> getAllItemTypes() {
        List<PackageType> packageTypesList = this.packageTypeRepository.findAll();
        return packageTypesList.stream().map(this.packageTypeMapper::mapToPackageType).toList();
    }
}
