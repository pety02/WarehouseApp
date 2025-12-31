package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import com.example.warehouseapp.service.ItemTypeService;
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

@WebMvcTest(ItemTypeController.class)
class ItemTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemTypeService itemTypeService;

    @Test
    @WithMockUser(username = "testuser")
    void getAllItemTypes_ok() throws Exception {
        when(itemTypeService.getAllItemTypes()).thenReturn(List.of());

        mockMvc.perform(get("/api/item_types"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    void getItemTypeById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        ItemTypeResponseDTO dto = ItemTypeResponseDTO.builder()
                .id(id.toString())
                .name("Dairy")
                .build();

        when(itemTypeService.getItemTypeById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/item_types/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dairy"));
    }
}
