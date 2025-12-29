package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.service.LowStockAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Low Stock Alerts",
        description = "Operations related to low stock alerts and predictions"
)
public class LowStockAlertController {

    private final LowStockAlertService lowStockAlertService;

    @Operation(
            summary = "Get all low stock alerts",
            description = "Returns a list of all low stock alerts"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Low stock alerts retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<LowStockAlertResponseDTO>> getAllLowStockAlerts() {
        return ResponseEntity.ok(lowStockAlertService.getAllLowStockAlerts());
    }

    @Operation(
            summary = "Get low stock alert by ID",
            description = "Returns details of a specific low stock alert"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Low stock alert retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid alert ID"),
            @ApiResponse(responseCode = "404", description = "Low stock alert not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LowStockAlertResponseDTO> getLowStockAlertById(
            @Parameter(
                    description = "Low stock alert UUID",
                    required = true,
                    example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
            )
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(lowStockAlertService.getLowStockAlertById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create low stock alert (AI prediction)",
            description = """
                    Generates a new low stock alert using AI-based prediction.
                    The authenticated user is used as the creator of the alert.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Low stock alert created successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Prediction or processing failed")
    })
    @PostMapping
    public ResponseEntity<LowStockAlertResponseDTO> createLowStockAlert(
            @AuthenticationPrincipal Principal principal
    ) {
        LowStockAlertResponseDTO createdLowStockAlert =
                lowStockAlertService.predictLowStocks(principal.getName());

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
