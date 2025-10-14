package com.example.warehouseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class StockAdviceAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private StockAdvice advice;
    @Enumerated(EnumType.STRING)
    private AdviceActionType actionType;
    @OneToMany(mappedBy = "action")
    private List<Item> items;
    private Integer recommendedQuantity;
    private String actionReason;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private boolean isActioned;
    private Double confidence;
}