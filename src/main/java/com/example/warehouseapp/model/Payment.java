package com.example.warehouseapp.model;

import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private LocalDateTime paymentTimestamp;
    private String reason;
    private Double amount;
    private Currency currency;
    private Delivery delivery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}