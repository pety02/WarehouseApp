package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @GetMapping
    public ResponseEntity<List<PackageResponseDTO>> getAllPackages() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> getPackageById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping
    public ResponseEntity<PackageResponseDTO> createPackage(@RequestBody PackageCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> updatePackageById(@PathVariable(name = "id") Long id,
                                                                @RequestBody PackageUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}