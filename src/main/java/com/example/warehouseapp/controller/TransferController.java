package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.TransferService;
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
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Tag(
        name = "Transfers",
        description = "Transfer management endpoints (CRUD, authentication)"
)
public class TransferController {

    private final TransferService transferService;

    @Operation(
            summary = "Get all transfers",
            description = "Returns a list of all transfers"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfers retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAllTransfers() {
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @Operation(
            summary = "Get transfer by ID",
            description = "Returns transfer details by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Transfer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable UUID id) {
        return ResponseEntity.ok(transferService.getTransferById(id));
    }

    @Operation(
            summary = "Create a transfer",
            description = "Creates a new inventory transfer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transfer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<TransferResponseDTO> createTransfer(
            @RequestBody @Valid TransferCreateRequestDTO requestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        TransferResponseDTO created =
                transferService.createTransfer(requestDTO, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(
            summary = "Update a transfer",
            description = "Updates an existing transfer by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transfer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Transfer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> updateTransferById(
            @PathVariable UUID id,
            @RequestBody @Valid TransferUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(
                transferService.updateTransfer(id, requestDTO, principal.getName())
        );
    }

    @Operation(
            summary = "Delete a transfer",
            description = "Deletes a transfer by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Transfer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Transfer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransferById(@PathVariable UUID id) {
        transferService.deleteTransferById(id);
        return ResponseEntity.noContent().build();
    }
}
