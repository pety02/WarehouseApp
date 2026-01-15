package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.ItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.entites.Package;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
                                .map(Package::getName).toList() :  List.of()
                )
                .itemType(item.getType() != null ? item.getType().getName() : null)
                .build();
    }

    public Item mapToEntity(ItemCreateRequestDTO itemRequestDTO, Set<Package> packages,
                            Set<Currency> currencies, List<Location> locations, ItemType itemType) {
        return Item.builder()
                .name(itemRequestDTO.getName())
                .barcodeValue(itemRequestDTO.getBarcodeValue())
                .packages(packages)
                .currencies(currencies)
                .sellingPrice(itemRequestDTO.getSellingPrice())
                .expirationDateTime(itemRequestDTO.getExpirationDateTime())
                .createdAt(itemRequestDTO.getCreatedAt())
                .updatedAt(itemRequestDTO.getUpdatedAt())
                .createdBy(itemRequestDTO.getCreatedBy())
                .updatedBy(itemRequestDTO.getUpdatedBy())
                .locations(locations)
                .type(itemType)
                .build();
    }
}
