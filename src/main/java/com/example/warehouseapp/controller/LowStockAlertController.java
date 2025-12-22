package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.service.LowStockAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/api/low_stock_alerts")
@RequiredArgsConstructor
public class LowStockAlertController {
    private final LowStockAlertService lowStockAlertService;

    @Operation(summary = "Get a list of all low stock alerts", description = "Returns a list of all low stock alerts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<LowStockAlertResponseDTO>> getAllLowStockAlerts() {
        return ResponseEntity.ok(this.lowStockAlertService.getAllLowStockAlerts());
    }

    @Operation(summary = "Get a low stock alert by id", description = "Returns a low stock alert as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LowStockAlertResponseDTO> getLowStockAlertById(@PathVariable(name = "id") UUID id) {
        LowStockAlertResponseDTO responseDTO;

        try {
            responseDTO = this.lowStockAlertService.getLowStockAlertById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<LowStockAlertResponseDTO> createLowStockAlert(@AuthenticationPrincipal Principal principal) {
        LowStockAlertResponseDTO createdLowStockAlert = this.lowStockAlertService.predictLowStocks(principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLowStockAlert.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdLowStockAlert);
    }
}
