package com.example.warehouseapp.controller;

import com.example.warehouseapp.service.StorageTypeService;
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

@WebMvcTest(StorageTypeController.class)
class StorageTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StorageTypeService storageTypeService;

    @Test
    void getAllStorageTypes_ok() throws Exception {
        when(storageTypeService.getAllStorageTypes()).thenReturn(List.of());

        mockMvc.perform(get("/api/storage_types"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStorageType_ok() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(storageTypeService).deleteStorageTypeById(id);

        mockMvc.perform(delete("/api/storage_types/{id}", id))
                .andExpect(status().isOk());
    }
}
