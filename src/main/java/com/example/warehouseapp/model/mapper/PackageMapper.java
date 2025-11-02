package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageResponseDTO;
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
}