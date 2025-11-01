package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/package_types")
public class PackageTypeController {

    @GetMapping
    public ResponseEntity<List<PackageTypeResponseDTO>> getAllPackageTypes() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> getPackageTypeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping
    public ResponseEntity<PackageTypeResponseDTO> createPackageType(@RequestBody PackageCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> updatePackageTypeById(@PathVariable(name = "id") Long id,
                                                                        @RequestBody PackageUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePackageTypeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}