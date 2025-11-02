package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.mapper.EmployeeMapper;
import com.example.warehouseapp.repository.EmployeeCredentialsRepository;
import com.example.warehouseapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCredentialsRepository employeeCredentialsRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new NotFoundEntityException("No employees found");
        }

        return employees.stream()
                .map(employee -> {
                    // Get credentials (lazy-loaded or via repository)
                    EmployeeCredentials credentials = employee.getCredentials();

                    // If credentials are somehow missing, try fetching from repo or handle gracefully
                    if (credentials == null) {
                        credentials = employeeCredentialsRepository
                                .findEmployeeCredentialsByEmployeeId(employee.getId())
                                .orElseThrow(() -> new NotFoundEntityException(
                                        "Credentials not found for employee id: " + employee.getId()
                                ));
                    }

                    return employeeMapper.mapToResponseDTO(employee, credentials);
                })
                .toList();
    }

    public EmployeeResponseDTO getEmployeeById(UUID id){
        Employee employee = employeeRepository.findById(id
        ).orElseThrow(() -> new NotFoundEntityException("Employee not found"));
        EmployeeCredentials employeeCredentials = employeeCredentialsRepository
                .findEmployeeCredentialsByEmployeeId(employee
                        .getCredentials()
                        .getId())
                .orElseThrow(() -> new NotFoundEntityException("EmployeeCredentials not found"));

        return this.employeeMapper.mapToResponseDTO(employee, employeeCredentials);
    }
}
