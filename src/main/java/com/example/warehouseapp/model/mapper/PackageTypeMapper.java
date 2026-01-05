package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.entites.PackageType;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PackageTypeMapper {

    public PackageTypeResponseDTO mapToResponseDTO(PackageType packageType) {
        return PackageTypeResponseDTO
                .builder()
                .id(packageType.getId().toString())
                .name(packageType.getName())
                .build();
    }

    public PackageType mapToEntity(PackageTypeRequestDTO packageTypeCreateRequestDTO, Instant createDate, String user){
        return PackageType
                .builder()
                .name(packageTypeCreateRequestDTO.getName())
                .createdAt(createDate)
                .createdBy(user)
                .updatedAt(null)
                .updatedBy(null)
                .build();
    }
}
