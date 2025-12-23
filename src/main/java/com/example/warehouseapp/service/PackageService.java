package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Package;
import com.example.warehouseapp.model.mapper.PackageMapper;
import com.example.warehouseapp.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    public PackageResponseDTO getPackageById(UUID id) {
        Package exisitingPackage = this.packageRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Package not found"));

        return this.packageMapper.mapToResponseDTO(exisitingPackage);
    }

    public List<PackageResponseDTO> getAllPackages() {
        List<Package> packagesList = this.packageRepository.findAll();
        return packagesList.stream().map(this.packageMapper::mapToResponseDTO).toList();
    }

    public PackageResponseDTO createPackage(PackageCreateRequestDTO packageRequestDTO) {
        return this.packageMapper.mapToResponseDTO(
                this.packageRepository.save(
                        this.packageMapper.mapToEntity(packageRequestDTO)
                )
        );
    }

    public PackageResponseDTO updatePackage(UUID id, PackageUpdateRequestDTO packageUpdateRequestDTO) {
        Package existingPackage = this.packageRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Package not found"));

        this.packageMapper.updatePackage(existingPackage, packageUpdateRequestDTO);

        Package updatedPackage = this.packageRepository.save(existingPackage);
        return this.packageMapper.mapToResponseDTO(updatedPackage);
    }

    public void deletePackageById(UUID id) {
        this.packageRepository.deleteById(id);
    }
}
