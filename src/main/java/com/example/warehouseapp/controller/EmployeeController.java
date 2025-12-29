package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeLoginRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import com.example.warehouseapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(
        name = "Employees",
        description = "Employee management endpoints (CRUD, authentication)"
)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(
            summary = "Get all employees",
            description = "Returns a list of all employees"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Operation(
            summary = "Get employee by ID",
            description = "Returns employee details for the given employee ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "400", description = "Invalid employee ID"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @Parameter(
                    description = "Employee UUID",
                    example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                    required = true
            )
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create a new employee",
            description = "Registers a new employee in the system"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Employee creation request",
                    required = true
            )
            @RequestBody @Valid EmployeeCreateRequestDTO employeeRequestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        EmployeeResponseDTO createdEmployee =
                employeeService.createEmployee(employeeRequestDTO, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdEmployee);
    }

    @Operation(
            summary = "Update employee",
            description = "Updates an existing employee by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(
            @Parameter(description = "Employee UUID", required = true)
            @PathVariable UUID id,
            @RequestBody @Valid EmployeeUpdateRequestDTO employeeRequestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employeeRequestDTO, principal.getName())
        );
    }

    @Operation(
            summary = "Employee login",
            description = "Authenticates an employee using credentials"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> login(
            @RequestBody @Valid EmployeeLoginRequestDTO employeeRequestDTO
    ) {
        return ResponseEntity.ok(employeeService.login(employeeRequestDTO));
    }

    @Operation(
            summary = "Logout employee",
            description = "Logs out the currently authenticated employee"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logout successful")
    })
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete employee",
            description = "Deletes an employee by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(
            @Parameter(description = "Employee UUID", required = true)
            @PathVariable UUID id
    ) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
