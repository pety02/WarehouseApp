package com.example.warehouseapp.model;

import java.time.LocalDate;
import java.util.Currency;
public class AccountRecord {
    private Long id;
    private Double amount;
    private Currency currency;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}