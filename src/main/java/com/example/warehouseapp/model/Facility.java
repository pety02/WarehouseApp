package com.example.warehouseapp.model;

import java.time.LocalDateTime;
import java.util.Map;
public class Facility extends AccountRecordOwner {
    private Address address;
    private FacilityType facilityType;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Employee manager;
    private Map<Item, Integer> itemsStocks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}