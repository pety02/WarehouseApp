package com.example.warehouseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class StockPredictionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Item item;
    @ManyToOne(optional = false)
    private Location location;
    private Integer predictedQuantity;
    private Double confidenceScore;
    private LocalDateTime predictionTimestamp;
    private String modelName;
    private String modelVersion;
}