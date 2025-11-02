package com.example.warehouseapp.model.dto;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
