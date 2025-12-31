package com.example.warehouseapp.model.dto;
import com.example.warehouseapp.model.entites.Address;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponseDTO {
    private String id;
    private String name;
    private Address address;
    private String managerId;
    private String managerName;
    private String managerSurname;
    private String managerEmail;
    private String managerPhoneNumber;
}
