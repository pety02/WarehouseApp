package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.entites.Package;
import com.example.warehouseapp.model.mapper.ItemMapper;
import com.example.warehouseapp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final PackageRepository packageRepository;
    private final CurrencyRepository currencyRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final LocationRepository locationRepository;

    @Transactional(readOnly = true)
    public ItemResponseDTO getItemById(UUID id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
        return itemMapper.mapToResponseDTO(item);
    }

    @Transactional
    public ItemResponseDTO createItem(ItemCreateRequestDTO dto) {

        ItemType type = itemTypeRepository.findById(UUID.fromString(dto.getType()))
                .orElseThrow(() -> new EntityNotFoundException("Item type not found"));

        Set<Package> packages = packageRepository.getAllByIds(
                dto.getPackages().stream().map(UUID::fromString).toList()
        );

        Set<Currency> currencies = currencyRepository.getAllByIds(
                dto.getCurrencies().stream().map(UUID::fromString).toList()
        );

        List<Location> locations = locationRepository.getAllByIds(
                dto.getLocations().stream().map(UUID::fromString).toList()
        );

        Item item = itemMapper.mapToEntity(dto, packages, currencies, locations, type);
        item.setCreatedAt(Instant.now());

        return itemMapper.mapToResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public ItemResponseDTO updateItem(UUID id, ItemUpdateRequestDTO dto) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (dto.getName() != null) item.setName(dto.getName());
        if (dto.getBarcodeValue() != null) item.setBarcodeValue(dto.getBarcodeValue());
        if (dto.getSellingPrice() != null) item.setSellingPrice(dto.getSellingPrice());
        if (dto.getExpirationDateTime() != null) item.setExpirationDateTime(dto.getExpirationDateTime());

        if (dto.getType() != null) {
            ItemType type = itemTypeRepository.findById(UUID.fromString(dto.getType()))
                    .orElseThrow(() -> new EntityNotFoundException("Item type not found"));
            item.setType(type);
        }

        if (dto.getPackages() != null) {
            item.setPackages(packageRepository.getAllByIds(
                    dto.getPackages().stream().map(UUID::fromString).toList()
            ));
        }

        if (dto.getCurrencies() != null) {
            item.setCurrencies(currencyRepository.getAllByIds(
                    dto.getCurrencies().stream().map(UUID::fromString).toList()
            ));
        }

        if (dto.getLocations() != null) {
            item.setLocations(locationRepository.getAllByIds(
                    dto.getLocations().stream().map(UUID::fromString).toList()
            ));
        }

        item.setUpdatedAt(Instant.now());

        return itemMapper.mapToResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public void deleteItem(UUID id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item not found");
        }
        itemRepository.deleteById(id);
    }
}