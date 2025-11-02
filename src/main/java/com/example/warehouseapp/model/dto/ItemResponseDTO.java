package com.example.warehouseapp.model.dto;

import lombok.*;
import java.util.List;
import org.springframework.data.util.Pair;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private String id;
    private String name;
    private String barcodeValue;
    private String expirationDateTime;
    private Double sellingPrice;
    private List<String> currencies;
    private List<Pair<String, String>> packages;
    private String itemType;
}
