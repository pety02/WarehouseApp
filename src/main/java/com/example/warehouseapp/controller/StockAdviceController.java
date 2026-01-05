package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.StockAdviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/api/stock_advices")
@RequiredArgsConstructor
@Tag(
        name = "Stock Advices",
        description = "Stock Advices management endpoints (CRUD)"
)
public class StockAdviceController {

    private final StockAdviceService stockAdviceService;

    @Operation(summary = "Get all stock advices")
    @ApiResponse(responseCode = "200", description = "Stock advices retrieved")
    @GetMapping
    public ResponseEntity<List<StockAdviceResponseDTO>> getAllStockAdvices() {
        return ResponseEntity.ok(stockAdviceService.getAllStockAdvices());
    }

    @Operation(summary = "Get stock advice by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock advice retrieved"),
            @ApiResponse(responseCode = "404", description = "Stock advice not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StockAdviceResponseDTO> getStockAdviceById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(stockAdviceService.getStockAdviceById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create stock advice")
    @ApiResponse(responseCode = "201", description = "Stock advice created")
    @PostMapping
    public ResponseEntity<StockAdviceResponseDTO> createStockAdvice(
            @RequestBody @Valid StockAdviceCreateRequestDTO dto
    ) {
        StockAdviceResponseDTO created = stockAdviceService.createStockAdvice(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Update stock advice")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock advice updated"),
            @ApiResponse(responseCode = "404", description = "Stock advice not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StockAdviceResponseDTO> updateStockAdvice(
            @PathVariable UUID id,
            @RequestBody @Valid StockAdviceUpdateRequestDTO dto
    ) {
        try {
            return ResponseEntity.ok(stockAdviceService.updateStockAdvice(id, dto));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete stock advice")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Stock advice deleted"),
            @ApiResponse(responseCode = "404", description = "Stock advice not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockAdvice(@PathVariable UUID id) {
        try {
            stockAdviceService.deleteStockAdvice(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
