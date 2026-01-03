package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.entites.Item;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    public ItemResponseDTO mapToResponseDTO(Item item) {
        return ItemResponseDTO
                .builder()
                .id(item.getId() != null ? item.getId().toString() : null)
                .name(item.getName())
                .barcodeValue(item.getBarcodeValue())
                .expirationDateTime(item.getExpirationDateTime() != null ? item.getExpirationDateTime().toString() : null)
                .sellingPrice(item.getSellingPrice())
                .currencies(item.getCurrencies() != null ? item.getCurrencies().stream().map(Currency::getName).toList() : null)
                .packages(
                        item.getPackages() != null ? item.getPackages().stream()
                                .map(p -> Pair.of(p.getName(), p.getPiecesCount().toString()))
                                .toList() :  List.of()
                )
                .itemType(item.getType() != null ? item.getType().getName() : null)
                .build();
    }
}
