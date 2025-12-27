package com.example.warehouseapp.model.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageTypeUpdateRequestDTO {
    private String name;
}
