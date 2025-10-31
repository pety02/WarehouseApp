package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockAdviceAction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer recommendedQuantity;
    private String actionReason;
    private Boolean isActioned;
    private Double confidence;
    private String createdByModelVersion;
    private String updatedByModelVersion;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
}
