package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageTypeCreateRequestDTO {
    @NotBlank(message = "Storage type name is required")
    @Size(min = 2, max = 50, message = "Storage type name must be between 2 and 50 characters")
    private String name;
}