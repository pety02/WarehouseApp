package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.service.ItemTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/item_types")
@RequiredArgsConstructor
@Tag(
        name = "Item Types",
        description = "Item Type management endpoints (CRUD, authentication)"
)
public class ItemTypeController {
    private  final ItemTypeService itemTypeService;

    @Operation(summary = "Get a list of all item types", description = "Returns a list of all item types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<ItemTypeResponseDTO>> getAllItemTypes() {
        return ResponseEntity.ok(itemTypeService.getAllItemTypes());
    }

    @Operation(summary = "Get an item type by id", description = "Returns an item type as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemTypeResponseDTO> getItemTypeById(@PathVariable(name = "id") UUID id) {
        ItemTypeResponseDTO responseDTO;

        try {
            responseDTO = this.itemTypeService.getItemTypeById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }
}
