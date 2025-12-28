package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferItemCreateRequestDTO {
    private Integer quantity;
    private String itemId;
}
