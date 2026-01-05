package com.example.warehouseapp.model.entites;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @NotBlank(message = "ZIP code is required")
    @Pattern(
            regexp = "^[0-9]{4,6}$",
            message = "ZIP code must be between 4 and 6 digits"
    )
    private String zip;

    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @NotBlank(message = "Street number is required")
    @Size(max = 10, message = "Street number must not exceed 10 characters")
    private String no;
}