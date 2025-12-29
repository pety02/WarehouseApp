package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.service.TransferItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfer-items")
@RequiredArgsConstructor
@Tag(
        name = "Transfer Items",
        description = "Transfer Item management endpoints (CRUD, authentication)"
)
public class TransferItemController {

    private final TransferItemService transferItemService;

    @Operation(
            summary = "Get all transfer items",
            description = "Returns a list of all transfer items"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer items retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<TransferItemResponseDTO>> getAllTransferItems() {
        return ResponseEntity.ok(transferItemService.getAllTransferItems());
    }

    @Operation(
            summary = "Get transfer items by transfer ID",
            description = "Returns all items belonging to a specific transfer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer items retrieved successfully")
    })
    @GetMapping("/transfer/{transferId}")
    public ResponseEntity<List<TransferItemResponseDTO>> getTransferItemsByTransferId(
            @PathVariable UUID transferId
    ) {
        return ResponseEntity.ok(
                transferItemService.getTransferItemsByTransferId(transferId)
        );
    }

    @Operation(
            summary = "Create a transfer item",
            description = "Creates a new item within a transfer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transfer item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping("/transfer/{transferId}")
    public ResponseEntity<TransferItemResponseDTO> createTransferItem(
            @PathVariable UUID transferId,
            @RequestBody @Valid TransferItemCreateRequestDTO requestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        TransferItemResponseDTO created =
                transferItemService.createTransferItem(
                        transferId,
                        principal.getName(),
                        requestDTO
                );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(
            summary = "Update a transfer item",
            description = "Updates an existing transfer item by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Transfer item not found")
    })
    @PutMapping("/{transferItemId}")
    public ResponseEntity<TransferItemResponseDTO> updateTransferItemById(
            @PathVariable UUID transferItemId,
            @RequestBody @Valid TransferItemUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(
                transferItemService.updateTransferItem(
                        transferItemId,
                        requestDTO,
                        principal.getName()
                )
        );
    }

    @Operation(
            summary = "Delete a transfer item",
            description = "Deletes a transfer item by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Transfer item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Transfer item not found")
    })
    @DeleteMapping("/{transferItemId}")
    public ResponseEntity<Void> deleteTransferItemById(@PathVariable UUID transferItemId) {
        transferItemService.deleteTransferItemById(transferItemId);
        return ResponseEntity.noContent().build();
    }
}
