package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeRoleResponseDTO;
import com.example.warehouseapp.service.EmployeeRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee_roles")
@RequiredArgsConstructor
public class EmployeeRoleController {
    private final EmployeeRoleService employeeRoleService;

    @Operation(summary = "Get a list of all employee roles", description = "Returns a list of all employee roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeRoleResponseDTO>> getAllEmployeeRoles() {
        return ResponseEntity.ok(this.employeeRoleService.getAllEmployeeRoles());
    }

    @Operation(summary = "Get an employee role by id", description = "Returns an employee role as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRoleResponseDTO> getEmployeeRoleById(@PathVariable(name = "id") UUID id) {
        EmployeeRoleResponseDTO responseDTO;

        try {
            responseDTO = this.employeeRoleService.getEmployeeRoleById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }
}
