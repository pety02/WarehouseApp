package com.example.warehouseapp.model.entites;

import jakarta.persistence.Embeddable;
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
    private String country;
    private String city;
    private String zip;
    private String street;
    private String no;
}