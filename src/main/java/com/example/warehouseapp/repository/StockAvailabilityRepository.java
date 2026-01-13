package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.StockAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockAvailabilityRepository extends JpaRepository<StockAvailability, UUID> {
    Optional<StockAvailability> getItemById(UUID itemId);

    @Query("""
    SELECT sa
    FROM StockAvailability sa
    JOIN FETCH sa.item
    WHERE sa.zone IN (
        SELECT wz
        FROM Location l
        JOIN l.warehouseZones wz
        WHERE l.id = :locationId
    )
    """)
    List<StockAvailability> findAllByLocationId(@Param("locationId") UUID locationId);

    @Query("""
    SELECT sa
    FROM StockAvailability sa
    JOIN FETCH sa.item
    WHERE sa.zone IN (
        SELECT wz
        FROM Location l
        JOIN l.warehouseZones wz
        WHERE l.id = :locationId
    ) AND sa.item.name = :name
    """)
    Optional<StockAvailability> getItemByLocationIdAndItemName(@Param("locationId") UUID locationId, @Param("name") String name);

    @Query("""
    SELECT sa
    FROM StockAvailability sa
    JOIN FETCH sa.item i
    LEFT JOIN FETCH i.currencies
    JOIN FETCH sa.zone z
    LEFT JOIN FETCH z.storageType
    WHERE sa.zone IN (
        SELECT wz
        FROM Location l
        JOIN l.warehouseZones wz
        WHERE l.id = :locationId
    )
""")
    List<StockAvailability> findAllByLocationIdWithItems(@Param("locationId") UUID locationId);
}
