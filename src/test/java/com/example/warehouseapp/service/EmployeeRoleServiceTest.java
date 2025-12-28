package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.model.entites.EmployeeRole;
import com.example.warehouseapp.model.mapper.EmployeeRoleMapper;
import com.example.warehouseapp.repository.EmployeeRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeRoleServiceTest {

    @Mock
    private EmployeeRoleRepository employeeRoleRepository;

    @Mock
    private EmployeeRoleMapper employeeRoleMapper;

    @InjectMocks
    private EmployeeRoleService employeeRoleService;

    @Test
    void getEmployeeRoleById_success() {
        UUID id = UUID.randomUUID();
        EmployeeRole role = EmployeeRole.builder().id(id).name("ADMIN").build();

        when(employeeRoleRepository.findById(id))
                .thenReturn(Optional.of(role));
        when(employeeRoleMapper.mapToResponseDTO(role))
                .thenReturn(EmployeeRoleResponseDTO.builder().id(id.toString()).build());

        EmployeeRoleResponseDTO dto = employeeRoleService.getEmployeeRoleById(id);

        assertEquals(id.toString(), dto.getId());
    }

    @Test
    void getEmployeeRoleById_notFound() {
        when(employeeRoleRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> employeeRoleService.getEmployeeRoleById(UUID.randomUUID()));
    }
}
