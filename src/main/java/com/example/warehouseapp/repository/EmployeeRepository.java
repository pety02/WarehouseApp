package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeByCredentialsId(UUID credentialsId);
    @Query("SELECT e FROM Employee e JOIN FETCH e.credentials WHERE e.credentials.email = :email")
    Optional<Employee> findEmployeeByEmail(String email);
    @Query("SELECT e FROM Employee e JOIN FETCH e.location WHERE e.location.id = :locationId")
    List<Employee> findAllByLocationId(UUID locationId);
}
