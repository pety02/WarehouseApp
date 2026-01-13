package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseZone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private StorageType storageType;
    @ManyToMany(mappedBy = "warehouseZones", fetch = FetchType.LAZY)
    private List<Location> locations;
}
