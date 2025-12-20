package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginRequestDTO {
    private String email;
    private String password;
}