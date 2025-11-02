package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
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
    })
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(@PathVariable(name = "id") UUID id) {
        // TODO: to fix LocationService and TransferService to use the current location in their quires.
        // TODO: to document properly all end points.
        return null;
    }

    @Operation(summary = "Get an item by id", description = "Returns an item as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}/items/{itid}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable(name = "id") UUID id,
                                                       @PathVariable(name = "itid") UUID itid) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a list of all employees", description = "Returns a list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get an employee by id", description = "Returns an employee as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}/employees/{eid}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable(name = "id") UUID id,
                                                               @PathVariable(name = "eid") UUID eid) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a list of all stock advices by id", description = "Returns a list of all stock advices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/stock_advices")
    public ResponseEntity<List<StockAdviceResponseDTO>> getAllStockAdvices(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a stock advice by id", description = "Returns a stock advice as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}/stock_advices/{stid}")
    public ResponseEntity<StockAdviceResponseDTO> getStockAdviceById(@PathVariable(name = "id") UUID id,
                                                                     @PathVariable(name = "stid") UUID stid) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a list of all stock advice actions", description = "Returns a list of all advice actions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/stock_advice_actions")
    public ResponseEntity<List<StockAdviceActionResponseDTO>> getAllStockAdviceActions(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a stock advice action by id", description = "Returns a stock advice action as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - the request was mistaken"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}/stock_advice_actions/{staaid}")
    public ResponseEntity<StockAdviceActionResponseDTO> getStockAdviceActionById(@PathVariable(name = "id") UUID id,
                                                                                 @PathVariable(name = "staaid") UUID staaid) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a list of all stock availabilities", description = "Returns a list of all stock availabilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/stock_availabilities")
    public ResponseEntity<List<StockAvailabilityResponseDTO>> getAllStockAvailabilities(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @Operation(summary = "Get a list of all warehouse zones", description = "Returns a list of all warehouse zones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/{id}/warehouse_zones")
    public ResponseEntity<List<WarehouseZoneResponseDTO>> getAllWarehouseZones(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> createLocation(@PathVariable(name = "id") UUID id,
                                                              @RequestBody @Valid LocationCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ItemResponseDTO> createItem(@PathVariable(name = "id") UUID id,
                                                      @RequestBody @Valid ItemCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/stock_advices")
    public ResponseEntity<StockAdviceResponseDTO> createStockAdvice(@PathVariable(name = "id") UUID id,
                                                                    @RequestBody @Valid StockAdviceActionCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/stock_advice_actions")
    public ResponseEntity<StockAdviceActionResponseDTO> createStockAdviceAction(@PathVariable(name = "id") UUID id,
                                                                                @RequestBody @Valid StockAdviceActionCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/stock_availabilities")
    public ResponseEntity<StockAvailabilityResponseDTO> createStockAvailability(@PathVariable(name = "id") UUID id,
                                                                                @RequestBody @Valid StockAvailabilityCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/warehouse_zones")
    public ResponseEntity<WarehouseZoneResponseDTO> createWarehouseZone(@PathVariable(name = "id") UUID id,
                                                                        @RequestBody @Valid WarehouseZoneCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> updateLocation(@PathVariable(name = "id") UUID id,
                                                              @RequestBody @Valid LocationUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/items/{itid}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable(name = "id") UUID id,
                                                      @PathVariable(name = "itid") UUID itid,
                                                      @RequestBody @Valid ItemUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/stock_advices/{stid}")
    public ResponseEntity<StockAdviceResponseDTO> updateStockAdvice(@PathVariable(name = "id") UUID id,
                                                                    @PathVariable(name = "stid") UUID stid,
                                                                    @RequestBody @Valid StockAdviceUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/stock_advice_actions/{staaid}")
    public ResponseEntity<StockAdviceActionResponseDTO> updateStockAdviceAction(@PathVariable(name = "id") UUID id,
                                                                                @PathVariable(name = "staaid") UUID staaid,
                                                                                @RequestBody @Valid StockAdviceActionUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/stock_availabilities/{staid}")
    public ResponseEntity<StockAvailabilityResponseDTO> updateStockAvailability(@PathVariable(name = "id") UUID id,
                                                                                @PathVariable(name = "staid") UUID staid,
                                                                                @RequestBody @Valid StockAvailabilityUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable(name = "id") UUID id) {
        // TODO: to implement the logic here
    }

    @DeleteMapping("/{id}/items/{itid}")
    public void deleteItemById(@PathVariable(name = "id") UUID id,
                               @PathVariable(name = "itid") UUID itid) {
        // TODO: to implement the logic here
    }

    @DeleteMapping("/{id}/employee_roles/{erid}")
    public void deleteEmployeeRoleById(@PathVariable(name = "id") UUID id,
                                       @PathVariable(name = "erid") UUID erid) {
        // TODO: to implement the logic here
    }

    @DeleteMapping("/{id}/stock_availabilities/{staid}")
    public void deleteStockAvailabilityById(@PathVariable(name = "id") UUID id,
                                            @PathVariable(name = "staid") UUID staid) {
        // TODO: to implement the logic here
    }

    @DeleteMapping("/{id}/warehouse_zones/{wzid}")
    public void deleteWarehouseZoneById(@PathVariable(name = "id") UUID id,
                                        @PathVariable(name = "wzid") UUID wzid) {
        // TODO: to implement the logic here
    }
}