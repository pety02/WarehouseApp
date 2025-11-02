package com.example.warehouseapp.model.dto;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDTO {
    private String id;
    private String name;
    private String address;
    private String managerName;
    private String managerSurname;
    private String managerEmail;
    private String managerPhoneNumber;
}
