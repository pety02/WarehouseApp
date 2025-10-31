package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String barcodeValue;
    private Instant expirationDateTime;
    private Double sellingPrice;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Currency> currencies;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Package> packages;
    @ManyToOne(fetch = FetchType.EAGER)
    private ItemType type;
}
