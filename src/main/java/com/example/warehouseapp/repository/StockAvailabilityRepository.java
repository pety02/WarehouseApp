package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.StockAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockAvailabilityRepository extends JpaRepository<StockAvailability, UUID> {
}
