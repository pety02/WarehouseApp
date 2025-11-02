package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeLoginRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee_credentials")
public class EmployeeCredentialsController {
    @GetMapping("/login")
    public ResponseEntity<EmployeeResponseDTO> loginEmployee(@RequestBody @Valid EmployeeLoginRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

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
