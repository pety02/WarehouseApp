package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseZoneRepository extends JpaRepository<WarehouseZone, UUID> {
    Optional<WarehouseZone> getWarehouseZoneById(UUID zoneId);
    @Query("SELECT wz FROM Location l JOIN l.warehouseZones wz WHERE l.id = :locationId")
    List<WarehouseZone>  findAllByLocationId(UUID locationId);
}
