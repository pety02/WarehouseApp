package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.service.PackageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<PackageTypeResponseDTO> createPackageType(@RequestBody @Valid PackageTypeRequestDTO obj) {
        PackageTypeResponseDTO createdLowStockAlert = this.packageTypeService.createPackageType(obj);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLowStockAlert.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdLowStockAlert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> updatePackageTypeById(@PathVariable(name = "id") UUID id,
                                                                        @RequestBody @Valid PackageTypeRequestDTO obj) {
        return ResponseEntity.ok(this.packageTypeService.updatePackageType(id, obj));
    }

    @DeleteMapping("/{id}")
    public void deletePackageTypeById(@PathVariable(name = "id") UUID id) {
        this.packageTypeService.deletePackageTypeById(id);
    }
}
