package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCredentialsCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeCredentialsResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
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

    public EmployeeCredentials mapToEntity(EmployeeCredentialsCreateRequestDTO employeeCredentialsRequest,
                                           String user, LocalDate today) {
        return EmployeeCredentials
                .builder()
                .email(employeeCredentialsRequest.getEmail())
                .phoneNumber(employeeCredentialsRequest.getPhoneNumber())
                .password(employeeCredentialsRequest.getPassword())
                .createdBy(user)
                .updatedBy(user)
                .createdAt(Instant.from(today))
                .updatedAt(Instant.from(today))
                .build();
    }
}