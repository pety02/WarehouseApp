package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class EmployeeCredentialsServiceIntegrationTest {

    @Autowired
    private EmployeeCredentialsService service;

    @Autowired
    private EmployeeCredentialsRepository repository;

    @Test
    void saveAndFindByEmail_realDb() {
        EmployeeCredentials creds = EmployeeCredentials.builder()
                .email("real@test.com")
                .password("123")
                .build();

        repository.save(creds);

        EmployeeCredentials found = service.findByEmail("real@test.com");

        assertEquals("real@test.com", found.getEmail());
    }
}
