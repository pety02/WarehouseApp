package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageCreateRequestDTO;
import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.model.dto.PackageUpdateRequestDTO;
import com.example.warehouseapp.service.PackageService;
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
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/packages")
@RequiredArgsConstructor
@Tag(
        name = "Packages",
        description = "Package management endpoints (CRUD, authentication)"
)
public class PackageController {

    private final PackageService packageService;

    @Operation(
            summary = "Get all packages",
            description = "Returns a list of all available packages"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Packages retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<PackageResponseDTO>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @Operation(
            summary = "Get package by ID",
            description = "Returns details of a specific package by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Package retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid package ID"),
            @ApiResponse(responseCode = "404", description = "Package not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> getPackageById(
            @Parameter(
                    description = "Package UUID",
                    required = true,
                    example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
            )
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(packageService.getPackageById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create a new package",
            description = "Creates a new package and returns the created package"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Package created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<PackageResponseDTO> createPackage(
            @RequestBody @Valid PackageCreateRequestDTO packageRequestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        Instant createDate = Instant.now();
        PackageResponseDTO createdPackage = packageService.createPackage(packageRequestDTO, createDate, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPackage.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdPackage);
    }

    @Operation(
            summary = "Update an existing package",
            description = "Updates package details by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Package updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Package not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> updatePackageById(
            @Parameter(
                    description = "Package UUID",
                    required = true
            )
            @PathVariable UUID id,
            @RequestBody @Valid PackageUpdateRequestDTO packageUpdateRequestDTO,
            @AuthenticationPrincipal Principal user
    ) {
        return ResponseEntity.ok(
                packageService.updatePackage(id, packageUpdateRequestDTO, Instant.now(), user.getName())
        );
    }

    @Operation(
            summary = "Delete a package",
            description = "Deletes a package by its ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Package deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Package not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackageById(
            @Parameter(
                    description = "Package UUID",
                    required = true
            )
            @PathVariable UUID id
    ) {
        packageService.deletePackageById(id);
        return ResponseEntity.noContent().build();
    }
}
