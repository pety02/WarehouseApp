package com.example.warehouseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class StockAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Item item;
    @ManyToOne(optional = false)
    private Location location;
    @OneToMany(mappedBy = "advice")
    private List<StockAdviceAction> actions;
    private LocalDateTime validUntil;
    private String modelVersion;
    private String reasoning;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private boolean isActioned;
    private Double confidence;
}