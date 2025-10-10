package com.example.warehouseapp.model;

import java.util.List;
public class DeliveryOffer {
    private Long id;
    private List<Supplier> suppliers;
    private List<Delivery> deliveries;
    private Double minWeight;
    private Double maxWeight;
    private Double price;
    private Currency currency;
}