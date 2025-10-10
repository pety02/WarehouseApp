package com.example.warehouseapp.model;

import java.util.List;
public class Region {
    private Long id;
    private String name;
    private Country country;
    private List<Locality> localities;
    private List<Address> addresses;
}