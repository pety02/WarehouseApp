package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Instant deliveryDateTime;

    private String remarks;

    private String createdBy;
    private String updatedBy;

    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Location sourceLocation;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Location destinationLocation;

    @OneToMany(
            mappedBy = "transfer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransferItem> items;
}
