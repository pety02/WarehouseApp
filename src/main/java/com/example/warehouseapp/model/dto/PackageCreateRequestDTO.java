package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageCreateRequestDTO {
    @NotBlank(message = "Package name is required")
    @Size(min = 2, max = 100, message = "Package name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Pieces count is required")
    @Min(value = 1, message = "Pieces count must be at least 1")
    @Max(value = 100_000, message = "Pieces count is too large")
    private Integer piecesCount;
}
