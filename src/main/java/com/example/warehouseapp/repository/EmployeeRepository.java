package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.WarehouseZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeByCredentialsId(UUID credentialsId);
    @Query("SELECT e FROM Employee e JOIN e.credentials c WHERE c.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);
    List<Employee> findAllByLocationId(UUID locationId);
}
