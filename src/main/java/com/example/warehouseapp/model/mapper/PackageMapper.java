package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Package;
import org.springframework.stereotype.Component;

import java.time.Instant;

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

    public void updatePackage(Package _package, PackageUpdateRequestDTO packageUpdateRequestDTO, Instant updateDate, String user) {
        _package.setName(packageUpdateRequestDTO.getName());
        _package.setPiecesCount(packageUpdateRequestDTO.getPiecesCount());
        _package.setUpdatedBy(user);
        _package.setUpdatedAt(updateDate);
    }

    public Package mapToEntity(com.example.warehouseapp.model.dto.PackageCreateRequestDTO packageRequestDTO, Instant createDate, String user) {
        return Package
                .builder()
                .name(packageRequestDTO.getName())
                .piecesCount(packageRequestDTO.getPiecesCount())
                .createdBy(user)
                .updatedBy(null)
                .createdAt(createDate)
                .updatedAt(null)
                .build();
    }
}
