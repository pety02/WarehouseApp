package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Package;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackageResponseDTO mapToResponseDTO(Package _package) {
        return PackageResponseDTO
                .builder()
                .id(_package.getId().toString())
                .name(_package.getName())
                .piecesCount(_package.getPiecesCount())
                .build();
    }

    public void updatePackage(Package _package, PackageUpdateRequestDTO packageUpdateRequestDTO) {
        _package.setName(packageUpdateRequestDTO.getName());
        _package.setPiecesCount(packageUpdateRequestDTO.getPiecesCount());
        _package.setUpdatedBy(packageUpdateRequestDTO.getUpdatedBy());
        _package.setUpdatedAt(packageUpdateRequestDTO.getUpdatedAt());
    }

    public Package mapToEntity(com.example.warehouseapp.model.dto.PackageCreateRequestDTO packageRequestDTO) {
        return Package
                .builder()
                .name(packageRequestDTO.getName())
                .piecesCount(packageRequestDTO.getPiecesCount())
                .createdBy(packageRequestDTO.getCreatedBy())
                .updatedBy(packageRequestDTO.getUpdatedBy())
                .createdAt(packageRequestDTO.getCreatedAt())
                .updatedAt(packageRequestDTO.getUpdatedAt())
                .build();
    }
}
