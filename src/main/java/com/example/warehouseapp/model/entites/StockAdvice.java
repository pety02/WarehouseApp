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
public class StockAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant validUntil;
    private String reasoning;
    private Boolean isActioned;
    private Double confidence;
    private String createdByModelVersion;
    private String updatedByModelVersion;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToMany(fetch = FetchType.EAGER)
    private List<StockAdviceAction> actions;
}