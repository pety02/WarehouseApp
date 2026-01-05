package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.InvalidDateException;
import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Package;
import com.example.warehouseapp.model.mapper.PackageMapper;
import com.example.warehouseapp.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public PackageResponseDTO createPackage(PackageCreateRequestDTO packageRequestDTO, Instant createDate, String user) {
        if(createDate.isBefore(Instant.now())) {
            throw new InvalidDateException("Invalid package create date.");
        }

        return this.packageMapper.mapToResponseDTO(
                this.packageRepository.save(
                        this.packageMapper.mapToEntity(packageRequestDTO, createDate, user)
                )
        );
    }

    public PackageResponseDTO updatePackage(UUID id, PackageUpdateRequestDTO packageUpdateRequestDTO, Instant updateDate, String user) {
        Package existingPackage = this.packageRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Package not found"));

        if(existingPackage.getCreatedAt().isAfter(updateDate)) {
            throw new InvalidDateException("Updated at cannot be before created at");
        }

        this.packageMapper.updatePackage(existingPackage, packageUpdateRequestDTO, updateDate, user);

        Package updatedPackage = this.packageRepository.save(existingPackage);
        return this.packageMapper.mapToResponseDTO(updatedPackage);
    }

    public void deletePackageById(UUID id) {
        this.packageRepository.deleteById(id);
    }
}
