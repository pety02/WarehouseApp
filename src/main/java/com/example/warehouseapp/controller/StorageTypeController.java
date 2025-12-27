package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO;
import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO;
import com.example.warehouseapp.service.StorageTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class StorageTypeController {
    private final StorageTypeService storageTypeService;

    @Operation(summary = "Get a list of all storage types", description = "Returns a list of all storage types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping
    public ResponseEntity<List<StorageTypeResponseDTO>> getAllStorageTypes() {
        return ResponseEntity.ok(storageTypeService.getAllStorageTypes());
    }

    @PostMapping
    public ResponseEntity<StorageTypeResponseDTO> createStorageType(@RequestBody @Valid StorageTypeCreateRequestDTO locationRequestDTO,
                                                                    @AuthenticationPrincipal Authentication authentication) {
        StorageTypeResponseDTO createdLocation = this.storageTypeService.createStorageType(locationRequestDTO, authentication.getName());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLocation.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageTypeResponseDTO> updateStorageTypeById(@PathVariable(name = "id") UUID id,
                                                                        @RequestBody @Valid StorageTypeUpdateRequestDTO storageTypeRequestDTO,
                                                                        @AuthenticationPrincipal Authentication authentication) {
        StorageTypeResponseDTO updatedStorageType = this.storageTypeService.updateStorageTypeById(id,
                storageTypeRequestDTO,
                authentication.getName());
        return ResponseEntity.ok(updatedStorageType);
    }

    @DeleteMapping("/{id}")
    public void deleteStorageTypeById(@PathVariable(name = "id") UUID id) {
        this.storageTypeService.deleteStorageTypeById(id);
    }
}
