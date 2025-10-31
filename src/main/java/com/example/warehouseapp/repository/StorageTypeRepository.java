package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageTypeRepository extends JpaRepository<StorageType, Long> {
}
