package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    @Query("SELECT t FROM Transfer t " +
            "JOIN FETCH t.sourceLocation " +
            "JOIN FETCH t.destinationLocation " +
            "WHERE t.id = :id")
    Optional<Transfer> findByIdWithLocations(@Param("id") UUID id);
}
