package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.entites.Employee;
import com.example.warehouseapp.model.entites.EmployeeCredentials;

public class EmployeeMapper {
    public EmployeeResponseDTO mapToResponseDTO(Employee employee, EmployeeCredentials  employeeCredentials) {
        return EmployeeResponseDTO
                .builder()
                .id(employee.getId().toString())
                .name(employee.getName())
                .surname(employee.getSurname())
                .uidNo(employee.getUidNo())
                .hireDate(employee.getHireDate())
                .fireDate(employee.getFireDate())
                .email(employeeCredentials.getEmail())
                .phoneNumber(employeeCredentials.getPhoneNumber())
                .role(employee.getRole().getName())
                .locationId(employee.getLocation().getId().toString())
                .locationName(employee.getLocation().getName())
                .build();
    }
}