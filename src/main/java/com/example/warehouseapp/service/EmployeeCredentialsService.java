package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.mapper.EmployeeCredentialsMapper;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeCredentialsService {
    private final EmployeeCredentialsRepository employeeCredentialsRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeCredentialsMapper employeeCredentialsMapper;


    EmployeeCredentials findByEmail(String email) {
        return this.employeeCredentialsRepository.findByEmail(email).orElseThrow();
    }

    EmployeeCredentials saveCredentials(EmployeeCredentials employeeCredentials) {
        return this.employeeCredentialsRepository.save(employeeCredentials);
    }
}
