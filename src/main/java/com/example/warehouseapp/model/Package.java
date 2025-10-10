package com.example.warehouseapp.model;

import net.sourceforge.barbecue.Barcode;

import java.time.LocalDateTime;
import java.util.Map;
public class Package {
    private Long id;
    private PackageType packageType;
    private Map<Item, Integer> items;
    private Double sellingPrice;
    private Currency currency;
    private LocalDateTime deliveryDate;
    private LocalDateTime expirationDate;
    private Barcode barcode;
    private Supplier supplier;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}