package com.example.warehouseapp.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private Long id;
    private OrderType orderType;
    private LocalDateTime orderTimestamp;
    private Map<Item, Integer> items;
    private AccountRecordOwner benefactor;
    private AccountRecordOwner recipient;
    private Delivery delivery;
}