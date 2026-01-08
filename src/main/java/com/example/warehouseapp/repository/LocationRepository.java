package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("""
    SELECT loc
    FROM Location loc
    LEFT JOIN FETCH loc.manager m
    LEFT JOIN FETCH m.credentials
    WHERE loc.id = :id
""")
    Optional<Location> findById(UUID id);
    Location findByAddressAndName(Address address, String name);
}
