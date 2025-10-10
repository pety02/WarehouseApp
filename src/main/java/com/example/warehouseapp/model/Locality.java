package com.example.warehouseapp.model;

import java.util.List;
public class Locality {
    private Long id;
    private String name;
    private Region region;
    private List<Street> streets;
    private List<Address> addresses;
}