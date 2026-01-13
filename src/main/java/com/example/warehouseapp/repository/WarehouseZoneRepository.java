package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseZoneRepository extends JpaRepository<WarehouseZone, UUID> {
    Optional<WarehouseZone> getWarehouseZoneById(UUID zoneId);

    @Query("""
    SELECT DISTINCT z
    FROM WarehouseZone z
    LEFT JOIN FETCH z.storageType
    LEFT JOIN FETCH z.locations l
    WHERE l.id = :locationId
""")
    List<WarehouseZone> findZonesByLocation(
            @Param("locationId") UUID locationId
    );

    @Query("""
        SELECT z FROM WarehouseZone z
        LEFT JOIN FETCH z.storageType st
        LEFT JOIN FETCH z.locations
        WHERE z.name = :name AND st.id = z.storageType.id
    """)
    Optional<WarehouseZone> getWarehouseZoneByName(String name);
}
