package com.example.warehouseapp.controller;

import com.example.warehouseapp.service.TransferItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferItemController.class)
class TransferItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransferItemService transferItemService;

    @Test
    @WithMockUser(username = "testuser")
    void getAllTransferItems_ok() throws Exception {
        when(transferItemService.getAllTransferItems()).thenReturn(List.of());

        mockMvc.perform(get("/api/transfer-items"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    void getTransferItemsByTransferId_ok() throws Exception {
        UUID transferId = UUID.randomUUID();

        when(transferItemService.getTransferItemsByTransferId(transferId))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/transfer-items/transfer/{id}", transferId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "{ADMIN}")
    void deleteTransferItem_ok() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(transferItemService).deleteTransferItemById(id);

        mockMvc.perform(delete("/api/transfer-items/{id}", id)
                        .with(csrf())
                )
                .andExpect(status().isNoContent());
    }
}
