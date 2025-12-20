package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.*;
import com.example.warehouseapp.model.mapper.*;
import com.example.warehouseapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    // Location
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    // Item
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    // Employee
    private final EmployeeService employeeService;

    // StockAdvice
    private final StockAdviceRepository stockAdviceRepository;
    private final StockAdviceMapper stockAdviceMapper;

    // StockAdviceAction
    private final StockAdviceActionRepository stockAdviceActionRepository;
    private final StockAdviceActionMapper stockAdviceActionMapper;

    // StockAvailability
    private final StockAvailabilityRepository stockAvailabilityRepository;
    private final StockAvailabilityMapper stockAvailabilityMapper;

    // WarehouseZone
    private final WarehouseZoneRepository warehouseZoneRepository;
    private final WarehouseZoneMapper warehouseZoneMapper;

    public List<LocationResponseDTO> getAllLocations() {
        List<Location> locationsList = this.locationRepository.findAll();
        return locationsList.stream().map(this.locationMapper::mapToResponseDTO).toList();
    }

    public LocationResponseDTO getLocationById(UUID id) {
        Location location = this.locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Location not found"));

        return this.locationMapper.mapToResponseDTO(location);
    }

    public Location getLocationByAddressAndName(String address, String name) {
        return this.locationRepository.findByAddressAndName(address, name);
    }

    public List<ItemResponseDTO> getAllItems() {
        List<Item> itemsList = this.itemRepository.findAll();
        return itemsList.stream().map(this.itemMapper::mapToResponseDTO).toList();
    }

    public ItemResponseDTO getItemById(UUID id) {
        Item item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Item not found"));

        return this.itemMapper.mapToResponseDTO(item);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    public EmployeeResponseDTO getEmployeeById(UUID id) {
        return this.employeeService.getEmployeeById(id);
    }

    public List<StockAdviceResponseDTO> getAllStockAdvices() {
        List<StockAdvice> stockAdviceList = this.stockAdviceRepository.findAll();
        return stockAdviceList.stream().map(this.stockAdviceMapper::mapToResponseDTO).toList();
    }

    public StockAdviceResponseDTO getStockAdviceById(UUID id) {
        StockAdvice stockAdvice = this.stockAdviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("StockAdvice not found"));

        return this.stockAdviceMapper.mapToResponseDTO(stockAdvice);
    }

    public List<StockAdviceActionResponseDTO> getAllStockAdviceActions() {
        List<StockAdviceAction> stockAdviceActionsList = this.stockAdviceActionRepository.findAll();
        return stockAdviceActionsList.stream().map(this.stockAdviceActionMapper::mapToResponseDTO).toList();
    }

    public StockAdviceActionResponseDTO getStockAdviceActionById(UUID id) {
        StockAdviceAction stockAdviceAction = this.stockAdviceActionRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("StockAdviceAction not found"));

        return this.stockAdviceActionMapper.mapToResponseDTO(stockAdviceAction);
    }

    public List<StockAvailabilityResponseDTO> getAllStockAvailabilities() {
        List<StockAvailability> stockAvailabilitiesList = this.stockAvailabilityRepository.findAll();
        return stockAvailabilitiesList.stream().map(this.stockAvailabilityMapper::mapToResponseDTO).toList();
    }

    public List<WarehouseZoneResponseDTO> getAllWarehouseZones() {
        List<WarehouseZone> warehouseZonesList = this.warehouseZoneRepository.findAll();
        return warehouseZonesList.stream().map(this.warehouseZoneMapper::mapToResponseDTO).toList();
    }
}
