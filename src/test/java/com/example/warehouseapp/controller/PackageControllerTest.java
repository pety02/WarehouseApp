package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageResponseDTO;
import com.example.warehouseapp.service.PackageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PackageController.class)
class PackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PackageService packageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPackages_ok() throws Exception {
        when(packageService.getAllPackages()).thenReturn(List.of());

        mockMvc.perform(get("/api/packages"))
                .andExpect(status().isOk());
    }

    @Test
    void getPackageById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        PackageResponseDTO dto = PackageResponseDTO.builder()
                .id(id.toString())
                .build();

        when(packageService.getPackageById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/packages/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void deletePackage_ok() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(packageService).deletePackageById(id);

        mockMvc.perform(delete("/api/packages/{id}", id))
                .andExpect(status().isOk());
    }
}
