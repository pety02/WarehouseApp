package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.StockAdviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockAdviceController.class)
class StockAdviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StockAdviceService stockAdviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "testuser")
    void getAllStockAdvices_returns200() throws Exception {
        Mockito.when(stockAdviceService.getAllStockAdvices())
                .thenReturn(List.of(StockAdviceResponseDTO.builder().id("1").build()));

        mockMvc.perform(get("/api/stock_advices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    @WithMockUser(username = "testuser")
    void getStockAdviceById_found() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(stockAdviceService.getStockAdviceById(id))
                .thenReturn(StockAdviceResponseDTO.builder().id(id.toString()).build());

        mockMvc.perform(get("/api/stock_advices/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    @WithMockUser(username = "testuser")
    void getStockAdviceById_notFound() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(stockAdviceService.getStockAdviceById(id))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/stock_advices/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void createStockAdvice_returns201() throws Exception {
        StockAdviceCreateRequestDTO request = StockAdviceCreateRequestDTO.builder()
                .validUntil("2025-01-01")
                .reasoning("Stock is running low")
                .confidence(0.9)
                .isActioned(false)
                .actions(Map.of("order", "increase stock by 50 units"))
                .build();

        StockAdviceResponseDTO response = StockAdviceResponseDTO.builder()
                .id(UUID.randomUUID().toString())
                .build();

        Mockito.when(stockAdviceService.createStockAdvice(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/stock_advices")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(response.getId()));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void updateStockAdvice_notFound() throws Exception {
        UUID id = UUID.randomUUID();

        StockAdviceUpdateRequestDTO dto = StockAdviceUpdateRequestDTO.builder()
                .validUntil("2026-01-31") // ISO date format yyyy-MM-dd
                .reasoning("Stock levels are low and require immediate action")
                .isActioned(false)
                .confidence(0.75)
                .actions(Map.of("order", "increase stock by 50 units"))
                .build();

        Mockito.when(stockAdviceService.updateStockAdvice(Mockito.eq(id), Mockito.any()))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(put("/api/stock_advices/{id}", id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles = "{ADMIN}")
    void deleteStockAdvice_noContent() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/stock_advices/{id}", id)
                        .with(csrf())
                )
                .andExpect(status().isNoContent());
    }
}
