package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.StockPredictionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPredictionLogRepository extends JpaRepository<StockPredictionLog,Long> {
}