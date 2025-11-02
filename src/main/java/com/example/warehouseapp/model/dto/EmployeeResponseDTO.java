package com.example.warehouseapp.model.dto;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {
    private String id;
    private String name;
    private String surname;
    private String uidNo;
    private LocalDate hireDate;
    private LocalDate fireDate;
    private String email;
    private String phoneNumber;
    private String role;
    private String locationId;
    private String locationName;
}
