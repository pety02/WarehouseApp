package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.StockAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockAvailabilityRepository extends JpaRepository<StockAvailability, UUID> {
    Optional<StockAvailability> getItemById(UUID itemId);
    @Query("SELECT sa FROM StockAvailability sa JOIN sa.item.locations l WHERE l = ?1")
    List<StockAvailability> findAllByLocationId(UUID locationId);
}
