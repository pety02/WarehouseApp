package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LowStockAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private LocalDate alertDate;
    private String message;
    private Integer actualCount;
    private Integer neededCount;
    private String recommendations;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private StockAvailability stockAvailability;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Employee> employees;
}
