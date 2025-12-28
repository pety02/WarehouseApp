package com.example.warehouseapp.controller;

import com.example.warehouseapp.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LocationService locationService;
    @MockitoBean
    private ItemService itemService;
    @MockitoBean
    private EmployeeService employeeService;
    @MockitoBean
    private WarehouseZoneService warehouseZoneService;
    @MockitoBean
    private StockAvailabilityService stockAvailabilityService;

    @Test
    void getAllLocations_ok() throws Exception {
        when(locationService.getAllLocations()).thenReturn(List.of());

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllItemsByLocation_ok() throws Exception {
        UUID id = UUID.randomUUID();

        when(itemService.getAllItemsByLocationId(id)).thenReturn(List.of());

        mockMvc.perform(get("/api/locations/{id}/items", id))
                .andExpect(status().isOk());
    }

    @Test
    void getAllWarehouseZones_ok() throws Exception {
        UUID id = UUID.randomUUID();

        when(warehouseZoneService.getAllWarehouseZonesByLocationId(id))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/locations/{id}/warehouse_zones", id))
                .andExpect(status().isOk());
    }
}
