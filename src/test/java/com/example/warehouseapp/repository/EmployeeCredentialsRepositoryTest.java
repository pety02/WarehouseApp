package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false"
})
class EmployeeCredentialsRepositoryTest {

    @Autowired
    private EmployeeCredentialsRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByEmail_success() {
        EmployeeCredentials creds = repository.save(
                EmployeeCredentials.builder()
                        .email("test@mail.com")
                        .password("pwd")
                        .build()
        );

        Optional<EmployeeCredentials> result =
                repository.findByEmail("test@mail.com");

        assertTrue(result.isPresent());
        assertEquals(creds.getId(), result.get().getId());
    }

    @Test
    void findEmployeeCredentialsByEmployeeId_success() {
        EmployeeCredentials creds = repository.save(
                EmployeeCredentials.builder()
                        .email("x@mail.com")
                        .password("pwd")
                        .build()
        );

        Employee employee = employeeRepository.save(
                Employee.builder().credentials(creds).isActive(true).build()
        );

        Optional<EmployeeCredentials> result =
                repository.findEmployeeCredentialsByEmployeeId(employee.getId());

        assertTrue(result.isPresent());
    }
}
