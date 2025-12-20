package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeByCredentialsId(UUID credentialsId);
}
