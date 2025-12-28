package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import com.example.warehouseapp.service.LowStockAlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LowStockAlertController.class)
class LowStockAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LowStockAlertService lowStockAlertService;

    @Test
    void getAllLowStockAlerts_ok() throws Exception {
        when(lowStockAlertService.getAllLowStockAlerts()).thenReturn(List.of());

        mockMvc.perform(get("/api/low_stock_alerts"))
                .andExpect(status().isOk());
    }

    @Test
    void getLowStockAlertById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        LowStockAlertResponseDTO dto = LowStockAlertResponseDTO.builder()
                .id(id.toString())
                .build();

        when(lowStockAlertService.getLowStockAlertById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/low_stock_alerts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }
}
