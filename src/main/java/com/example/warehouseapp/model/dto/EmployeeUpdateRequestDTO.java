package com.example.warehouseapp.model.dto;

import com.example.warehouseapp.model.entites.Address;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequestDTO {
    private LocalDate fireDate;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private String locationName;
    private Address locationAddress;
}