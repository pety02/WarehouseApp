package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, UUID> {
}
