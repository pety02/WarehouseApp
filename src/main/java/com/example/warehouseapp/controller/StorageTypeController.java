package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.StorageTypeCreateRequestDTO;
import com.example.warehouseapp.model.dto.StorageTypeResponseDTO;
import com.example.warehouseapp.model.dto.StorageTypeUpdateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/storage_types")
public class StorageTypeController {

    @GetMapping
    public ResponseEntity<List<StorageTypeResponseDTO>> getAllStorageTypes() {
        // TODO: to implement the logic here
        return null;
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
