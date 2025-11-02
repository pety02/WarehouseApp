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
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<StorageTypeResponseDTO> createStorageType(@RequestBody @Valid StorageTypeCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageTypeResponseDTO> updateStorageTypeById(@PathVariable(name = "id") Long id,
                                                                        @RequestBody @Valid StorageTypeUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStorageTypeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }
}
