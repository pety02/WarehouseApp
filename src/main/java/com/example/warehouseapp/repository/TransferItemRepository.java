package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.TransferItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferItemRepository extends JpaRepository<TransferItem,Long> {
}