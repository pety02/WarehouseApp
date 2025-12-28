package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.service.TransferItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class TransferItemController {

    private final TransferItemService transferItemService;

    @Operation(summary = "Get a list of all transfer items", description = "Returns a list of all transfer items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<TransferItemResponseDTO>> getAllTransferItems() {
        List<TransferItemResponseDTO> items =
                transferItemService.getAllTransferItems();

        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Get transfer items by transfer id", description = "Returns all items for a given transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/transfer/{transferId}")
    public ResponseEntity<List<TransferItemResponseDTO>> getTransferItemsByTransferId(
            @PathVariable UUID transferId
    ) {
        List<TransferItemResponseDTO> items =
                transferItemService.getTransferItemsByTransferId(transferId);

        return ResponseEntity.ok(items);
    }

    @PostMapping("/transfer/{transferId}")
    public ResponseEntity<TransferItemResponseDTO> createTransferItem(
            @PathVariable UUID transferId,
            @RequestBody @Valid TransferItemCreateRequestDTO obj,
            @AuthenticationPrincipal Principal principal
    ) {
        TransferItemResponseDTO createdItem =
                transferItemService.createTransferItem(transferId, principal.getName(), obj);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdItem);
    }

    @PutMapping("/{transferItemId}")
    public ResponseEntity<TransferItemResponseDTO> updateTransferItemById(
            @PathVariable UUID transferItemId,
            @RequestBody @Valid TransferItemUpdateRequestDTO obj,
            @AuthenticationPrincipal Principal principal
    ) {
        TransferItemResponseDTO updatedItem =
                transferItemService.updateTransferItem(transferItemId, obj, principal.getName());

        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{transferItemId}")
    public void deleteTransferItemById(@PathVariable UUID transferItemId) {
        transferItemService.deleteTransferItemById(transferItemId);
    }
}
