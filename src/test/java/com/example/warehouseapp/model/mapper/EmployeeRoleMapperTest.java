package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.model.entites.EmployeeRole;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRoleMapperTest {

    @Test
    void mapToResponseDTO_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();

        EmployeeRole role = new EmployeeRole();
        role.setId(id);
        role.setName("Picker");

        EmployeeRoleMapper mapper = new EmployeeRoleMapper();
        EmployeeRoleResponseDTO dto = mapper.mapToResponseDTO(role);

        assertEquals(id.toString(), dto.getId());
        assertEquals("Picker", dto.getName());
    }
}
