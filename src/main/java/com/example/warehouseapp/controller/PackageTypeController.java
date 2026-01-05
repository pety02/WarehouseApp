package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageTypeRequestDTO;
import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.service.PackageTypeService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/package_types")
@RequiredArgsConstructor
@Tag(
        name = "Package Types",
        description = "Package Types management endpoints (CRUD, authentication)"
)
public class PackageTypeController {

    private final PackageTypeService packageTypeService;

    @Operation(
            summary = "Get all package types",
            description = "Returns a list of all available package types"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Package types retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<PackageTypeResponseDTO>> getAllPackageTypes() {
        return ResponseEntity.ok(packageTypeService.getAllIPackageTypes());
    }

    @Operation(
            summary = "Get package type by ID",
            description = "Returns a package type by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Package type retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid package type ID"),
            @ApiResponse(responseCode = "404", description = "Package type not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> getPackageTypeById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(packageTypeService.getPackageTypeById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create a package type",
            description = "Creates a new package type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Package type created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<PackageTypeResponseDTO> createPackageType(
            @RequestBody @Valid PackageTypeRequestDTO requestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        Instant createDate = Instant.now();
        PackageTypeResponseDTO created = packageTypeService.createPackageType(requestDTO, createDate, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(
            summary = "Update a package type",
            description = "Updates an existing package type by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Package type updated successfully"),
            @ApiResponse(responseCode = "404", description = "Package type not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PackageTypeResponseDTO> updatePackageTypeById(
            @PathVariable UUID id,
            @RequestBody @Valid PackageTypeRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(
                packageTypeService.updatePackageType(id, requestDTO)
        );
    }

    @Operation(
            summary = "Delete a package type",
            description = "Deletes a package type by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Package type deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Package type not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackageTypeById(@PathVariable UUID id) {
        packageTypeService.deletePackageTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
