package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, UUID> {

    @Query("""
        SELECT p FROM Package p WHERE p.id IN :ids
    """)
    Set<Package> getAllByIds(@Param("ids") List<UUID> ids);
}

