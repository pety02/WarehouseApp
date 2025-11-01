package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeCreateRequestDTO;
import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.model.dto.EmployeeUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@PathVariable(name = "id") Long id,
                                                                  @RequestBody EmployeeUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}
