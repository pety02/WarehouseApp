package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @Test
    void getAllEmployees_realDb() {
        repository.save(Employee.builder().isActive(true).build());

        List<EmployeeResponseDTO> employees = service.getAllEmployees();

        assertFalse(employees.isEmpty());
    }
}
