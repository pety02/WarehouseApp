package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCredentialsRepository credentialsRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void findEmployeeByCredentialsId_success() {
        EmployeeCredentials creds = credentialsRepository.save(
                EmployeeCredentials.builder()
                        .email("test@mail.com")
                        .password("pwd")
                        .build()
        );

        Employee employee = Employee.builder()
                .credentials(creds)
                .isActive(true)
                .build();

        employee = employeeRepository.save(employee);

        Optional<Employee> result =
                employeeRepository.findEmployeeByCredentialsId(creds.getId());

        assertTrue(result.isPresent());
        assertEquals(employee.getId(), result.get().getId());
    }

    @Test
    void findEmployeeByEmail_success() {
        EmployeeCredentials creds = credentialsRepository.save(
                EmployeeCredentials.builder()
                        .email("email@test.com")
                        .password("pwd")
                        .build()
        );

        Employee employee = Employee.builder()
                .credentials(creds)
                .isActive(true)
                .build();

        employeeRepository.save(employee);

        Optional<Employee> result =
                employeeRepository.findEmployeeByEmail("email@test.com");

        assertTrue(result.isPresent());
    }

    @Test
    void findAllByLocationId_success() {
        // Persist the location first so the FK works
        Location location = Location.builder()
                .name("L1")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Springfield")
                        .zip("12345")
                        .country("USA")
                        .build())
                .build();

        locationRepository.save(location);

        // Persist employees with cascade (optional)
        Employee e1 = Employee.builder()
                .location(location)
                .isActive(true)
                .build();

        Employee e2 = Employee.builder()
                .location(location)
                .isActive(true)
                .build();

        employeeRepository.saveAll(List.of(e1, e2));

        List<Employee> employees = employeeRepository.findAllByLocationId(location.getId());

        assertEquals(2, employees.size());
    }
}