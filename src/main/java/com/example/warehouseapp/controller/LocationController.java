package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final ItemService itemService;
    private final EmployeeService employeeService;
    private final WarehouseZoneService warehouseZoneService;
    private final StockAvailabilityService stockAvailabilityService;

    @Operation(summary = "Get a list of all locations", description = "Returns a list of all locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<LocationResponseDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @Operation(summary = "Get a location by id", description = "Returns a location as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The location was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> getLocationById(@PathVariable(name = "id") UUID id) {
        LocationResponseDTO responseDTO;

        try {
            responseDTO = this.locationService.getLocationById(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Get a list of all items", description = "Returns a list of all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The items was not found")
    })
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(@PathVariable(name = "id") UUID id) {
        List<ItemResponseDTO> responseDTOs;

        try {
            responseDTOs = this.itemService.getAllItemsByLocationId(id);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(
                    employeeService.getAllEmployeesByLocationId(id)
            );
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get a list of all stock availabilities", description = "Returns a list of all stock availabilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/stock_availabilities")
    public ResponseEntity<List<StockAvailabilityResponseDTO>> getAllStockAvailabilities(@PathVariable(name = "id") UUID id) {
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

    @Operation(summary = "Get a list of all warehouse zones", description = "Returns a list of all warehouse zones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/warehouse_zones")
    public ResponseEntity<List<WarehouseZoneResponseDTO>> getAllWarehouseZones(@PathVariable(name = "id") UUID id) {
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
