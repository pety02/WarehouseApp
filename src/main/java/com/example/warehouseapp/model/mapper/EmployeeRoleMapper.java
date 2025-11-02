package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.model.entites.EmployeeRole;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRoleMapper {

    public EmployeeRoleResponseDTO mapToResponseDTO(EmployeeRole employeeRole){
        return EmployeeRoleResponseDTO
                .builder()
                .id(employeeRole.getId().toString())
                .name(employeeRole.getName())
                .build();
    }
}