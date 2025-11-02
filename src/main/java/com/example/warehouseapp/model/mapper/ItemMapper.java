package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.entites.Item;
import org.springframework.data.util.Pair;

public class ItemMapper {
    public ItemResponseDTO mapToResponseDTO(Item item) {
        return ItemResponseDTO
                .builder()
                .id(item.getId().toString())
                .name(item.getName())
                .barcodeValue(item.getBarcodeValue())
                .expirationDateTime(item.getExpirationDateTime().toString())
                .sellingPrice(item.getSellingPrice())
                .currencies(item.getCurrencies().stream().map(Currency::getName).toList())
                .packages(
                        item.getPackages().stream()
                                .map(p -> Pair.of(p.getName(), p.getPiecesCount().toString()))
                                .toList()
                )
                .itemType(item.getType().getName())
                .build();
    }
}
