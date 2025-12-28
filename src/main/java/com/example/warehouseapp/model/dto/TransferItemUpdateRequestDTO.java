package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferItemUpdateRequestDTO {
    private Integer quantity;
    private String itemId;
}
