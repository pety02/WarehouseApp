package com.example.warehouseapp.model;

import java.util.List;
public class PackageType {
    private Long id;
    private String name;
    private Double price;
    private Currency currency;
    private Double width;
    private Double height;
    private Double depth;
    private Double weight;
    private List<Package> packages;
}