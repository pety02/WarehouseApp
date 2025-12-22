package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class CurrencyResponseDTO {
    private String id;
    private String name;
    private String abbreviation;
}
