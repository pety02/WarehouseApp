package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseZoneRepository extends JpaRepository<WarehouseZone, Long> {
}
