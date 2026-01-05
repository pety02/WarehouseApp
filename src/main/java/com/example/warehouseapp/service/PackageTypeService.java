package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.entites.PackageType;
import com.example.warehouseapp.model.mapper.PackageTypeMapper;
import com.example.warehouseapp.repository.PackageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackageTypeService {
    private final PackageTypeRepository packageTypeRepository;
    private final PackageTypeMapper packageTypeMapper;

    public PackageTypeResponseDTO getPackageTypeById(UUID id) {
        PackageType packageType = this.packageTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("PackageType not found"));

        return this.packageTypeMapper.mapToResponseDTO(packageType);
    }

    public List<PackageTypeResponseDTO> getAllIPackageTypes() {
        List<PackageType> packageTypesList = this.packageTypeRepository.findAll();
        return packageTypesList.stream().map(this.packageTypeMapper::mapToResponseDTO).toList();
    }

    public PackageTypeResponseDTO createPackageType(PackageTypeRequestDTO packageTypeRequestDTO, Instant createDate, String user) {
        PackageType savedPackageType = this.packageTypeRepository.save(this.packageTypeMapper.mapToEntity(packageTypeRequestDTO, createDate, user));
        return this.packageTypeMapper.mapToResponseDTO(savedPackageType);
    }

    public PackageTypeResponseDTO updatePackageType(UUID id, PackageTypeRequestDTO packageTypeRequestDTO) {
        PackageType existingPackageType = this.packageTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("PackageType not found"));

        existingPackageType.setName(packageTypeRequestDTO.getName());

        PackageType updatedPackageType = this.packageTypeRepository.save(existingPackageType);
        return this.packageTypeMapper.mapToResponseDTO(updatedPackageType);
    }

    public void deletePackageTypeById(UUID id) {
        this.packageTypeRepository.deleteById(id);
    }
}
