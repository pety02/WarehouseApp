package com.example.warehouseapp.model;

import java.time.LocalDateTime;

public class StockMovement {
    private Long id;
    private Item item;
    private Facility sourceFacility;
    private Facility destinationFacility;
    private int quantity;
    private LocalDateTime movementDate;
    private Employee performedBy;
    private String reason;
}