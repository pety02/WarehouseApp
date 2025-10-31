package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.LowStockAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowStockAlertRepository extends JpaRepository<LowStockAlert, Long> {
}
