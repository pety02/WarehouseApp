package com.example.warehouseapp.model.dto;

import com.example.warehouseapp.model.entites.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequestDTO {

    @NotNull(message = "Fire date is required")
    @PastOrPresent(message = "Fire date cannot be in the future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fireDate;

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

    @NotBlank(message = "Role is required")
    @Size(max = 30, message = "Role must not exceed 30 characters")
    private String role;

    @NotBlank(message = "Location name is required")
    @Size(max = 100, message = "Location name must not exceed 100 characters")
    private String locationName;

    @NotNull(message = "Location address is required")
    @Valid
    private Address locationAddress;
}