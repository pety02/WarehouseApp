package com.example.warehouseapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Currency;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class AccountRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Currency currency;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}