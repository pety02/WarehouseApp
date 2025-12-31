package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.service.LowStockAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Low Stock Alerts", description = "AI-generated low stock predictions")
public class LowStockAlertController {

    private final LowStockAlertService lowStockAlertService;

    @Operation(summary = "Create low stock alert (AI prediction)",
            description = "Generates a new low stock alert using AI-based prediction")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Low stock alert created successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Prediction or processing failed")
    })
    @PostMapping
    public ResponseEntity<LowStockAlertResponseDTO> createLowStockAlert(@AuthenticationPrincipal Principal principal) {
        LowStockAlertResponseDTO dto = lowStockAlertService.predictLowStocks(principal.getName());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getAlertDate()) // or some generated ID
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @Operation(summary = "Get a single low stock alert by ID",
            description = "Returns the low stock alert identified by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Low stock alert retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Low stock alert not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LowStockAlertResponseDTO> getLowStockAlertById(@PathVariable UUID id) {
        LowStockAlertResponseDTO dto = lowStockAlertService.getLowStockAlertById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Get all low stock alerts",
            description = "Returns a list of all low stock alerts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Low stock alerts retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<LowStockAlertResponseDTO>> getAllLowStockAlerts() {
        List<LowStockAlertResponseDTO> dtos = lowStockAlertService.getAllLowStockAlerts();
        return ResponseEntity.ok(dtos);
    }
}
