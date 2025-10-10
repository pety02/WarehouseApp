package com.example.warehouseapp.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
public class Facility extends AccountRecordOwner {
    private Address address;
    private List<Employee> employees;
    private List<EmployeeRole> roles;
    private FacilityType facilityType;
    private LocalDateTime openingDateTime;
    private LocalDateTime closingDateTime;
    private Employee manager;
    private Map<Item, Integer> itemsStocks;
}