package com.example.warehouseapp.model.dto;

import com.example.warehouseapp.model.entites.Address;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String uidNo;
    private String role;
    private String locationName;
    private Address locationAddress;
}