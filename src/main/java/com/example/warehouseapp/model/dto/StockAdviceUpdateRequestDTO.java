package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdviceUpdateRequestDTO {
    @NotBlank(message = "Valid until date is required")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "Valid until must be in format yyyy-MM-dd"
    )
    private String validUntil;

    @NotBlank(message = "Reasoning is required")
    @Size(min = 10, max = 1000, message = "Reasoning must be between 10 and 1000 characters")
    private String reasoning;

    @NotNull(message = "Actioned flag is required")
    private Boolean isActioned;

    @NotNull(message = "Confidence is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Confidence must be at least 0.0")
    @DecimalMax(value = "1.0", inclusive = true, message = "Confidence must be at most 1.0")
    private Double confidence;

    @NotNull(message = "Actions are required")
    @Size(min = 1, message = "At least one action is required")
    private Map<
            @NotBlank(message = "Action key must not be blank") String,
            @NotBlank(message = "Action value must not be blank") String
            > actions;
}
