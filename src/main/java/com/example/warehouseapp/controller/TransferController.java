package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.TransferService;
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
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @Operation(summary = "Get a list of all transfers", description = "Returns a list of all transfers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAllTransfers() {
        List<TransferResponseDTO> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }

    @Operation(summary = "Get a transfer by id", description = "Returns a transfer as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(
            @PathVariable UUID id
    ) {
        TransferResponseDTO transfer = transferService.getTransferById(id);
        return ResponseEntity.ok(transfer);
    }

    @PostMapping
    public ResponseEntity<TransferResponseDTO> createTransfer(
            @RequestBody @Valid TransferCreateRequestDTO transferRequestDTO,
            @AuthenticationPrincipal Principal user
    ) {
        TransferResponseDTO createdTransfer =
                this.transferService.createTransfer(transferRequestDTO, user.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransfer.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdTransfer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> updateTransferById(
            @PathVariable UUID id,
            @RequestBody @Valid TransferUpdateRequestDTO transferRequestDTO,
            @AuthenticationPrincipal Principal user
    ) {
        TransferResponseDTO updatedTransfer =
                this.transferService.updateTransfer(id, transferRequestDTO, user.getName());

        return ResponseEntity.ok(updatedTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteTransferById(@PathVariable UUID id) {
        this.transferService.deleteTransferById(id);
    }
}
