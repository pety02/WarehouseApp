package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EmployeeCredentialsService {
    private final EmployeeCredentialsRepository employeeCredentialsRepository;


    EmployeeCredentials findByEmail(String email) {
        return this.employeeCredentialsRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Invalid email or password"
        ));
    }

    EmployeeCredentials saveCredentials(EmployeeCredentials employeeCredentials) {
        return this.employeeCredentialsRepository.save(employeeCredentials);
    }
}
