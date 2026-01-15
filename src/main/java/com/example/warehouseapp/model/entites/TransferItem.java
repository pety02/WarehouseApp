package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transfer_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Transfer transfer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    private String createdBy;
    private String updatedBy;

    private Instant createdAt;
    private Instant updatedAt;
}
