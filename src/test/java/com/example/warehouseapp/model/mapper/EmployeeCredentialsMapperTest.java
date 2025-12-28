package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCredentialsResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCredentialsMapperTest {

    private EmployeeCredentialsMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new EmployeeCredentialsMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapAllFieldsCorrectly() {
        UUID credentialsId = UUID.randomUUID();
        Instant now = Instant.now();

        EmployeeCredentials credentials = new EmployeeCredentials();
        credentials.setId(credentialsId);
        credentials.setEmail("john.doe@example.com");
        credentials.setPhoneNumber("+359888123456");
        credentials.setPassword("hashed-password");
        credentials.setCreatedAt(now);
        credentials.setUpdatedAt(now);
        credentials.setCreatedBy("admin");
        credentials.setUpdatedBy("admin");

        Employee employee = new Employee();
        employee.setName("John");
        employee.setSurname("Doe");

        EmployeeCredentialsResponseDTO result =
                mapper.mapToResponseDTO(credentials, employee);

        assertEquals(credentialsId.toString(), result.getId());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("+359888123456", result.getPhoneNumber());
        assertEquals("hashed-password", result.getPassword());
        assertEquals(now.toString(), result.getCreatedAt());
        assertEquals(now.toString(), result.getUpdatedAt());
        assertEquals("admin", result.getCreatedBy());
        assertEquals("admin", result.getUpdatedBy());
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getSurname());
    }
}
