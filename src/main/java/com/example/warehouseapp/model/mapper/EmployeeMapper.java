package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeLoginResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.entites.Location;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        return EmployeeResponseDTO
                .builder()
                .id(employee.getId() != null ? employee.getId().toString() : null)
                .name(employee.getName())
                .surname(employee.getSurname())
                .uidNo(employee.getUidNo())
                .hireDate(employee.getHireDate() != null ? employee.getHireDate().toString() : null)
                .fireDate(employee.getFireDate() != null ? employee.getFireDate().toString() : null)
                .email(employee.getCredentials() != null ? employee.getCredentials().getEmail() : null)
                .phoneNumber(employee.getCredentials() != null ? employee.getCredentials().getPhoneNumber() : null)
                .role(employee.getRole() != null ? employee.getRole().getName() : null)
                .locationId(employee.getLocation() != null ? employee.getLocation().getId().toString() : null)
                .locationName(employee.getLocation() != null ? employee.getLocation().getName() : null)
                .build();
    }

    public EmployeeLoginResponseDTO toLoginResponseDTO(Employee employee) {
        return EmployeeLoginResponseDTO
                .builder()
                .id(employee.getId() != null ? employee.getId().toString() : null)
                .fullName(employee.getName() + " " + employee.getSurname())
                .email(employee.getCredentials() != null ? employee.getCredentials().getEmail() : null)
                .locationId(employee.getLocation() != null ? employee.getLocation().getId().toString() : null)
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
                .updatedBy(null)
                .createdAt(today.atStartOfDay(ZoneId.systemDefault()).toInstant())
                .updatedAt(null)
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
        employee.setUpdatedAt(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return employee;
    }
}