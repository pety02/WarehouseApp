package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        return EmployeeResponseDTO
                .builder()
                .id(employee.getId().toString())
                .name(employee.getName())
                .surname(employee.getSurname())
                .uidNo(employee.getUidNo())
                .hireDate(employee.getHireDate().toString())
                .fireDate(employee.getFireDate().toString())
                .email(employee.getCredentials().getEmail())
                .phoneNumber(employee.getCredentials().getPhoneNumber())
                .role(employee.getRole().getName())
                .locationId(employee.getLocation().getId().toString())
                .locationName(employee.getLocation().getName())
                .build();
    }

    public Employee mapToEmployee(EmployeeCreateRequestDTO employeeCreateRequestDTO,
                                  EmployeeRole role, Location location, String user, LocalDate today) {
        return Employee
                .builder()
                .name(employeeCreateRequestDTO.getName())
                .surname(employeeCreateRequestDTO.getSurname())
                .uidNo(employeeCreateRequestDTO.getUidNo())
                .hireDate(today)
                .fireDate(null)
                .createdBy(user)
                .updatedBy(user)
                .createdAt(Instant.from(today))
                .updatedAt(Instant.from(today))
                .credentials(null)
                .role(role)
                .location(location)
                .credentials(null)
                .build();
    }

    public Employee updateEmployee(Employee employee, EmployeeUpdateRequestDTO employeeRequestDTO,
                                   EmployeeRole role, Location location, String user, LocalDate today) {
        employee.setFireDate(employeeRequestDTO.getFireDate());
        employee.setRole(role);
        employee.setLocation(location);
        employee.setUpdatedBy(user);
        employee.setUpdatedAt(Instant.from(today));

        return employee;
    }
}