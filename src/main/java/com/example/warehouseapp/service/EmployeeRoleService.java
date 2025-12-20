package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.mapper.EmployeeRoleMapper;
import com.example.warehouseapp.repository.EmployeeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeRoleService {
    private final EmployeeRoleRepository employeeRoleRepository;
    private final EmployeeRoleMapper employeeRoleMapper;

    public List<EmployeeRoleResponseDTO> getAllEmployeeRoles() {
        List<EmployeeRole> employeeRolesList = this.employeeRoleRepository.findAll();
        return employeeRolesList.stream().map(this.employeeRoleMapper::mapToResponseDTO).toList();
    }

    public EmployeeRoleResponseDTO getEmployeeRoleById(UUID id) {
        EmployeeRole employeeRole = this.employeeRoleRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("EmployeeRole not found"));

        return this.employeeRoleMapper.mapToResponseDTO(employeeRole);
    }

    public EmployeeRole getEmployeeRoleByName(String roleName) {
        return this.employeeRoleRepository.findByName(roleName);
    }
}