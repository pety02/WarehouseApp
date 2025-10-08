package com.example.warehouseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sourceforge.barbecue.Barcode;

import java.util.List;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ItemType type;
    private Barcode barcode;
    @ElementCollection
    private List<String> allergens;
    @ManyToOne
    private Supplier supplier;
    private Double price;
    private Boolean isExpired;
    private LocalDateTime deliveryDate;
    private LocalDateTime expirationDate;
}