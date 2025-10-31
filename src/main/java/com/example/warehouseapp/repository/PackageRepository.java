package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
}
