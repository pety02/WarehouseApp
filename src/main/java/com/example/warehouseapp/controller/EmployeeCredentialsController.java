package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeLoginRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee_credentials")
public class EmployeeCredentialsController {

    @Operation(summary = "Get an employee by username nad password packaged in EmployeeLoginRequestDTO object",
            description = "Returns an employee as per the username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> loginEmployee(@RequestBody @Valid EmployeeLoginRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Logout an employee by id", description = "Logout an employee as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/logout")
    public void logoutEmployee() {
        // TODO: to implement the logic here
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(@RequestBody @Valid EmployeeCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeCredentialsById(@PathVariable(name = "id") Long id,
                                                                             @RequestBody @Valid EmployeeUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeCredentialsById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}
