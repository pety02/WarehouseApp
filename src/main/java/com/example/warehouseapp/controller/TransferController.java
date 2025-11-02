package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Operation(summary = "Get a list of all transfers", description = "Returns a list of all transfers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAllTransfers() {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a transfer by id", description = "Returns a transfer as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDTO> createTransfer(@RequestBody @Valid TransferCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> updateTransferById(@PathVariable(name = "id") UUID id,
                                                                  @RequestBody @Valid TransferUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTransferById(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
    }

    @Operation(summary = "Get a list of all transfer items", description = "Returns a list of all transfer items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/transfer_items")
    public ResponseEntity<List<TransferItemResponseDTO>> getAllTransferItems(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/transfer_items")
    public ResponseEntity<TransferItemResponseDTO> createTransferItem(@PathVariable(name = "id") UUID id,
                                                                      @RequestBody @Valid TransferItemCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/transfer_items/{tiid}")
    public ResponseEntity<TransferItemResponseDTO> updateTransferItemById(@PathVariable(name = "id") UUID id,
                                                                          @PathVariable(name = "tiid") UUID tiid,
                                                                          @RequestBody @Valid TransferItemUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}/transfer_items/{tiid}")
    public void deleteTransferItemById(@PathVariable(name = "id") UUID id, @PathVariable(name = "tiid") UUID tiid) {
        // TODO: to implement the logic here
    }
}