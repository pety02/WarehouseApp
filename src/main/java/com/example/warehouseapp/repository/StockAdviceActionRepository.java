package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.StockAdviceAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdviceActionRepository extends JpaRepository<StockAdviceAction,Long> {
}