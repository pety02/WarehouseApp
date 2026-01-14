package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.service.TransferService;
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

@WebMvcTest(TransferController.class)
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransferService transferService;

    @Test
    @WithMockUser(username = "testuser")
    void getAllTransfers_ok() throws Exception {
        when(transferService.getAllTransfers()).thenReturn(List.of());

        mockMvc.perform(get("/api/transfers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    void getTransferById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        TransferResponseDTO dto = TransferResponseDTO.builder()
                .id(id.toString())
                .build();

        /*when(transferService.getTransferById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/transfers/{id}", id))
                .andExpect(status().isOk());*/
    }

    @Test
    @WithMockUser(username = "admin", roles = "{ADMIN}")
    void deleteTransfer_ok() throws Exception {
        /*UUID id = UUID.randomUUID();

        doNothing().when(transferService).deleteTransferById(id);

        mockMvc.perform(delete("/api/transfers/{id}", id)
                        .with(csrf())
                )
                .andExpect(status().isNoContent());*/
    }
}
