package com.example.warehouseapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}