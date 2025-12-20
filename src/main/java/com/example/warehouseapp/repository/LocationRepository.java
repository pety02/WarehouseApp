package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    Location findByAddressAndName(String address, String name);
}
