package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/employee_roles")
public class EmployeeRoleController {

    @GetMapping
    public ResponseEntity<List<EmployeeRoleResponseDTO>> getAllEmployeeRoles() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRoleResponseDTO> getEmployeeRoleById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }
}
