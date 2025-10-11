package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.StockAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdviceRepository extends JpaRepository<StockAdvice,Long> {
}