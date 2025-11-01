package com.example.warehouseapp.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponseDTO {
    private UUID id;
    private String name;
    private String abbreviation;
}
