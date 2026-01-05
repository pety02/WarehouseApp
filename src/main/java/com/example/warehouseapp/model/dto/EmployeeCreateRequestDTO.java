package com.example.warehouseapp.model.dto;

import com.example.warehouseapp.model.entites.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(0[0-9]{9}|\\+359[0-9]{9})$",
            message = "Phone number format is invalid"
    )
    private String phoneNumber;

    @NotBlank(message = "UID number is required")
    @Size(min=10, max=10, message = "UID number must be 10 characters")
    private String uidNo;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Location name is required")
    private String locationName;

    @NotNull(message = "Location address is required")
    @Valid
    private Address locationAddress;
}