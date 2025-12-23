package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseZoneRepository extends JpaRepository<WarehouseZone, UUID> {
    public Optional<WarehouseZone> getWarehouseZoneById(UUID zoneId);
}
