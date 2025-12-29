package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO;
import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO;
import com.example.warehouseapp.service.StorageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/storage_types")
@RequiredArgsConstructor
@Tag(
        name = "Storage Types",
        description = "Storage Types management endpoints (CRUD, authentication)"
)
public class StorageTypeController {

    private final StorageTypeService storageTypeService;

    @Operation(
            summary = "Get all storage types",
            description = "Returns a list of all storage types"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Storage types retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<StorageTypeResponseDTO>> getAllStorageTypes() {
        return ResponseEntity.ok(storageTypeService.getAllStorageTypes());
    }

    @Operation(
            summary = "Create a storage type",
            description = "Creates a new storage type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Storage type created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<StorageTypeResponseDTO> createStorageType(
            @RequestBody @Valid StorageTypeCreateRequestDTO requestDTO,
            @AuthenticationPrincipal Authentication authentication
    ) {
        StorageTypeResponseDTO created =
                storageTypeService.createStorageType(requestDTO, authentication.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(
            summary = "Update a storage type",
            description = "Updates an existing storage type by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Storage type updated successfully"),
            @ApiResponse(responseCode = "404", description = "Storage type not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StorageTypeResponseDTO> updateStorageTypeById(
            @PathVariable UUID id,
            @RequestBody @Valid StorageTypeUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal Authentication authentication
    ) {
        return ResponseEntity.ok(
                storageTypeService.updateStorageTypeById(
                        id,
                        requestDTO,
                        authentication.getName()
                )
        );
    }

    @Operation(
            summary = "Delete a storage type",
            description = "Deletes a storage type by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Storage type deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Storage type not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorageTypeById(@PathVariable UUID id) {
        storageTypeService.deleteStorageTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
