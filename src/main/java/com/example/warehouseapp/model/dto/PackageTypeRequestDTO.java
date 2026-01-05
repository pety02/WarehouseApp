package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageTypeRequestDTO {

    @NotBlank(message = "Package type name is required")
    @Size(min = 2, max = 50, message = "Package type name must be between 2 and 50 characters")
    private String name;
}