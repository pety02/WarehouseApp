package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, UUID> {
}
