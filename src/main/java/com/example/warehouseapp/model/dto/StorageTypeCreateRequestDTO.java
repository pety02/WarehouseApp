package com.example.warehouseapp.model.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageTypeCreateRequestDTO {
    private String name;
}
