package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeCredentialsRepository extends JpaRepository<EmployeeCredentials, UUID> {
    @Query("SELECT ec FROM Employee e JOIN e.credentials ec WHERE e.id = :id")
    Optional<EmployeeCredentials> findEmployeeCredentialsByEmployeeId(@Param("id") UUID id);
}
