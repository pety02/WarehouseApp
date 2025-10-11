package com.example.warehouseapp.model;

import jakarta.persistence.*;

@Entity
@Table
public class TransferItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Transfer transfer;
    @ManyToOne
    private Item item;
    private Integer quantity;
}