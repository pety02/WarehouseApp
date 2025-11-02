package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.entites.PackageType;
import org.springframework.stereotype.Component;

@Component
public class PackageTypeMapper {

    public PackageTypeResponseDTO mapToPackageType(PackageType packageType){
        return PackageTypeResponseDTO
                .builder()
                .id(packageType.getId().toString())
                .name(packageType.getName())
                .build();
    }
}
