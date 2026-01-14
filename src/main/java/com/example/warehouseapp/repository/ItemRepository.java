package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("""
    SELECT DISTINCT i
    FROM Item i
    JOIN i.locations l
    LEFT JOIN FETCH i.packages
    LEFT JOIN FETCH i.currencies
    LEFT JOIN FETCH i.type
    WHERE l.id = :locationId
    """)
    List<Item> findAllByLocationId(@Param("locationId") UUID locationId);

    @Query("""
        SELECT i FROM Item i
        LEFT JOIN FETCH i.packages
        WHERE i.id = :id
    """)
    Optional<Item> findItemById(UUID id);

    @Query("""
        SELECT i FROM Item i
        LEFT JOIN FETCH i.packages
        WHERE i.name = :name
    """)
    Optional<Item> getItemByName(String name);
}
