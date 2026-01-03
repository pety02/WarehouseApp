package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.LocationResponseDTO;
import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import com.example.warehouseapp.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class LocationServiceIntegrationTest {

    @Autowired
    private LocationService service;

    @Autowired
    private LocationRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCredentialsRepository employeeCredentialsRepository;

    @Test
    void getMultipleLocations_realDb() {
        // --- 1. Create addresses ---
        Address addr1 = Address.builder()
                .city("Sofia")
                .country("Bulgaria")
                .street("Main St")
                .no("1")
                .zip("1000")
                .build();

        Address addr2 = Address.builder()
                .city("Plovdiv")
                .country("Bulgaria")
                .street("Second St")
                .no("2")
                .zip("4000")
                .build();

        // --- 2. Create manager credentials ---
        EmployeeCredentials creds1 = EmployeeCredentials.builder()
                .email("manager1@company.com")
                .phoneNumber("+359888111111")
                .build();

        employeeCredentialsRepository.save(creds1);

        EmployeeCredentials creds2 = EmployeeCredentials.builder()
                .email("manager2@company.com")
                .phoneNumber("+359888222222")
                .build();

        employeeCredentialsRepository.save(creds2);

        // --- 3. Create managers ---
        Employee manager1 = Employee.builder()
                .name("John")
                .surname("Doe")
                .credentials(creds1)
                .build();

        Employee manager2 = Employee.builder()
                .name("Jane")
                .surname("Smith")
                .credentials(creds2)
                .build();

        // Save managers first so Hibernate knows they're persistent
        employeeRepository.saveAll(List.of(manager1, manager2));

        // --- 4. Create and save locations ---
        Location loc1 = Location.builder()
                .name("Sofia Office")
                .address(addr1)
                .manager(manager1)
                .createdAt(Instant.now())
                .build();

        Location loc2 = Location.builder()
                .name("Plovdiv Office")
                .address(addr2)
                .manager(manager2)
                .createdAt(Instant.now())
                .build();

        repository.saveAll(List.of(loc1, loc2));

        // --- 5. Fetch all locations ---
        List<LocationResponseDTO> locations = service.getAllLocations();

        // --- 6. Assertions ---
        assertFalse(locations.isEmpty(), "Locations list should not be empty");
        assertEquals(2, locations.size(), "Should have 2 locations");

        // Optional: check manager info is correctly mapped
        LocationResponseDTO sofiaDto = locations.stream()
                .filter(l -> l.getName().equals("Sofia Office"))
                .findFirst()
                .orElseThrow();

        assertEquals("John", sofiaDto.getManagerName());
        assertEquals("Doe", sofiaDto.getManagerSurname());
        assertEquals("manager1@company.com", sofiaDto.getManagerEmail());
    }
}