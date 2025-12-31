package com.example.warehouseapp.controller;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrencyService currencyService;

    @Test
    @WithMockUser(username = "testuser")
    void getCurrencyById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        CurrencyResponseDTO dto = CurrencyResponseDTO.builder()
                .id(id.toString())
                .name("Euro")
                .abbreviation("EUR")
                .build();

        when(currencyService.getCurrencyById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/currencies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Euro"));
    }

    @Test
    @WithMockUser(username = "testuser")
    void getCurrencyById_notFound() throws Exception {
        UUID id = UUID.randomUUID();

        when(currencyService.getCurrencyById(id))
                .thenThrow(new NotFoundEntityException("Not found"));

        mockMvc.perform(get("/api/currencies/{id}", id))
                .andExpect(status().isNotFound());
    }
}
