package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Tag(
        name = "Locations",
        description = "Location-related operations including items, employees, zones, and stock availability"
)
public class LocationController {

    private final LocationService locationService;
    private final ItemService itemService;
    private final EmployeeService employeeService;
    private final WarehouseZoneService warehouseZoneService;
    private final StockAvailabilityService stockAvailabilityService;

    @Operation(
            summary = "Get all locations",
            description = "Returns a list of all warehouse locations"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Locations retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<LocationResponseDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @Operation(
            summary = "Get location by ID",
            description = "Returns location details for the given location ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Location retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid location ID"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> getLocationById(
            @Parameter(
                    description = "Location UUID",
                    required = true,
                    example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
            )
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(locationService.getLocationById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get items by location",
            description = "Returns all items available at the specified location"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid location ID"),
            @ApiResponse(responseCode = "404", description = "Location or items not found")
    })
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(
            @Parameter(description = "Location UUID", required = true)
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(itemService.getAllItemsByLocationId(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get employees by location",
            description = "Returns all employees assigned to the given location"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid location ID"),
            @ApiResponse(responseCode = "404", description = "Location or employees not found")
    })
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
            @Parameter(description = "Location UUID", required = true)
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(employeeService.getAllEmployeesByLocationId(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get stock availability by location",
            description = "Returns stock availability for all items in the given location"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock availability retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid location ID"),
            @ApiResponse(responseCode = "404", description = "Stock availability not found")
    })
    @GetMapping("/{id}/stock_availabilities")
    public ResponseEntity<List<StockAvailabilityResponseDTO>> getAllStockAvailabilities(
            @Parameter(description = "Location UUID", required = true)
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(
                    stockAvailabilityService.getAllStockAvailabilitiesByLocationId(id)
            );
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get warehouse zones by location",
            description = "Returns all warehouse zones for the specified location"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Warehouse zones retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid location ID"),
            @ApiResponse(responseCode = "404", description = "Warehouse zones not found")
    })
    @GetMapping("/{id}/warehouse_zones")
    public ResponseEntity<List<WarehouseZoneResponseDTO>> getAllWarehouseZones(
            @Parameter(description = "Location UUID", required = true)
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(
                    warehouseZoneService.getAllWarehouseZonesByLocationId(id)
            );
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
