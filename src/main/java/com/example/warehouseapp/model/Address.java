package com.example.warehouseapp.model;

import java.util.List;
public class Address {
    private Long id;
    private AddressType addressType;
    private Country country;
    private Region region;
    private Street street;
    private Locality locality;
    private Postal postal;
    private List<Facility> facilities;
}