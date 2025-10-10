package com.example.warehouseapp.model;

import java.util.List;
public class Country {
    private Long id;
    private String fullName;
    private String abbreviation;
    private List<Region> regions;
    private List<Postal> postal;
    private List<Address> addresses;
}