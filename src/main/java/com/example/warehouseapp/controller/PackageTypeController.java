package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.service.PackageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/package_types")
@RequiredArgsConstructor
public class PackageTypeController {
    private final PackageTypeService packageTypeService;

    @Operation(summary = "Get a list of all package types", description = "Returns a list of all package types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<PackageTypeResponseDTO>> getAllPackageTypes() {
        return ResponseEntity.ok(this.packageTypeService.getAllIPackageTypes());
    }

    @Operation(summary = "Get a package type by id", description = "Returns a package type as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> getPackageTypeById(@PathVariable(name = "id") UUID id) {
        PackageTypeResponseDTO responseDTO;

        try {
            responseDTO = this.packageTypeService.getPackageTypeById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<PackageTypeResponseDTO> createPackageType(@RequestBody @Valid PackageCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> updatePackageTypeById(@PathVariable(name = "id") Long id,
                                                                        @RequestBody @Valid PackageUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePackageTypeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}
