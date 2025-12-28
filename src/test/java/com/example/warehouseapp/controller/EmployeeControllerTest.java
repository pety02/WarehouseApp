package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.EmployeeResponseDTO;
import com.example.warehouseapp.service.EmployeeService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployees_ok() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of());

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeById_ok() throws Exception {
        UUID id = UUID.randomUUID();

        EmployeeResponseDTO dto = EmployeeResponseDTO.builder()
                .id(id.toString())
                .name("Ivan")
                .surname("Petrov")
                .build();

        when(employeeService.getEmployeeById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ivan"));
    }

    @Test
    void deleteEmployee_ok() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(employeeService).deleteEmployeeById(id);

        mockMvc.perform(delete("/api/employees/{id}", id))
                .andExpect(status().isOk());
    }
}
