package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.TransferItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface TransferItemRepository extends JpaRepository<TransferItem, UUID> {
    @Query("SELECT TI FROM TransferItem TI WHERE TI.item.id =: id")
    List<TransferItem> findAllByTransferId(@Param("id") UUID Id);
}
