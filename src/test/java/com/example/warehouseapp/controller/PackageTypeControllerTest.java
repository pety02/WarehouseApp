package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.PackageTypeResponseDTO;
import com.example.warehouseapp.service.PackageTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PackageTypeController.class)
class PackageTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PackageTypeService packageTypeService;

    @Test
    @WithMockUser(username = "testuser")
    void getAllPackageTypes_ok() throws Exception {
        when(packageTypeService.getAllIPackageTypes()).thenReturn(List.of());

        mockMvc.perform(get("/api/package_types"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    void getPackageTypeById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        PackageTypeResponseDTO dto = PackageTypeResponseDTO.builder()
                .id(id.toString())
                .name("Box")
                .build();

        when(packageTypeService.getPackageTypeById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/package_types/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Box"));
    }
}
