package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCredentialsResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;

public class EmployeeCredentialsMapper {
    public EmployeeCredentialsResponseDTO mapToResponseDTO(EmployeeCredentials employeeCredentials, Employee employee) {
        return EmployeeCredentialsResponseDTO
                .builder()
                .id(employeeCredentials.getId().toString())
                .email(employeeCredentials.getEmail())
                .phoneNumber(employeeCredentials.getPhoneNumber())
                .password(employeeCredentials.getPassword())
                .createdAt(employeeCredentials.getCreatedAt().toString())
                .updatedAt(employeeCredentials.getUpdatedAt().toString())
                .createdBy(employeeCredentials.getCreatedBy())
                .updatedBy(employeeCredentials.getUpdatedBy())
                .name(employee.getName())
                .surname(employee.getSurname())
                .build();
    }
}