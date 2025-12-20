package com.example.warehouseapp.model.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCredentialsCreateRequestDTO {
    private String email;
    private String phoneNumber;
    private String password;
}
