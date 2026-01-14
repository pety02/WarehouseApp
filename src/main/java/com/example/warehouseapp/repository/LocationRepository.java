package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("SELECT l FROM Location l JOIN FETCH l.manager WHERE l.id = :locationId")
    Optional<Location> findByIdWithManager(@Param("locationId") UUID id);
    Location findByAddressAndName(Address address, String name);

    @Query("""
        SELECT l FROM Location l WHERE l.id IN :ids
    """)
    List<Location> getAllByIds(@Param("ids") List<UUID> ids);
}
