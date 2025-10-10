package com.example.warehouseapp.model;

import java.time.LocalDateTime;

public class Delivery {
    private Long id;
    private LocalDateTime deliveryTimestamp;
    private Payment payment;
    private Order order;
    private DeliveryOffer deliveryOffer;
}