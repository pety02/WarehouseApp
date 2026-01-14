package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Set;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    @Query("""
        SELECT c FROM Currency c WHERE c.id IN :ids
    """)
    Set<Currency> getAllByIds(@Param("ids") List<UUID> ids);
}
