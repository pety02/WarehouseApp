package com.example.warehouseapp.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCredentialsResponseDTO {
    private String id;
    private String email;
    private String phoneNumber;
    private String password;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
    private String name;
    private String surname;
}