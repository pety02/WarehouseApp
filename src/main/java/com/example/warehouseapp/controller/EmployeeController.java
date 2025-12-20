package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeLoginRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import com.example.warehouseapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Get a list of all employees", description = "Returns a list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @Operation(summary = "Get an employee by id", description = "Returns an employee as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable(name = "id") UUID id) {
        EmployeeResponseDTO responseDTO;

        try {
            responseDTO = this.employeeService.getEmployeeById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(
            @RequestBody @Valid EmployeeCreateRequestDTO employeeRequestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        EmployeeResponseDTO createdEmployee = employeeService
                .createEmployee(employeeRequestDTO, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEmployee.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid EmployeeUpdateRequestDTO employeeRequestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        EmployeeResponseDTO createdEmployee = employeeService
                .updateEmployee(id, employeeRequestDTO, principal.getName());
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<EmployeeResponseDTO> login(@RequestBody @Valid EmployeeLoginRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(this.employeeService.login(employeeRequestDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable(name = "id") UUID id) {
        this.employeeService.deleteEmployeeById(id);
    }
}