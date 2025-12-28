package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("SELECT i FROM Item i JOIN i.locations l WHERE l.id = :locationId")
    List<Item> findAllByLocationId(UUID locationId);
    Optional<Item> findItemById(UUID id);
}
