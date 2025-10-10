package com.example.warehouseapp.model;

import jakarta.persistence.*;
import net.sourceforge.barbecue.Barcode;

import java.util.List;

import java.time.LocalDateTime;
public class Item {
    private Long id;
    private String name;
    private ItemType type;
    private Barcode barcode;
    private List<Allergen> allergens;
    private Supplier supplier;
    private Double sellingPrice;
    private Currency currency;
    private Boolean isExpired;
    private LocalDateTime deliveryDate;
    private LocalDateTime expirationDate;
    private List<Package> packages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}