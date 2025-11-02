package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeCredentialsRepository extends JpaRepository<EmployeeCredentials, UUID> {
}
