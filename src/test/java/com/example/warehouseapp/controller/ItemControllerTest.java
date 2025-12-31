package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;

    @Test
    @WithMockUser(username = "testuser")
    void getItemById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        ItemResponseDTO dto = ItemResponseDTO.builder()
                .id(id.toString())
                .name("Milk")
                .build();

        when(itemService.getItemById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/items/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Milk"));
    }
}
