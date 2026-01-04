package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.repository.EmployeeRoleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class EmployeeRoleServiceIntegrationTest {

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Test
    void getAllEmployeeRoles_realDb() {
        employeeRoleRepository.save(
                EmployeeRole.builder().name("MANAGER").build()
        );

        List<EmployeeRoleResponseDTO> roles =
                employeeRoleService.getAllEmployeeRoles();

        assertFalse(roles.isEmpty());
    }
}
