package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.StockAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StockAdviceRepository extends JpaRepository<StockAdvice, UUID> {
    // TODO: should be implemented and tested
}
