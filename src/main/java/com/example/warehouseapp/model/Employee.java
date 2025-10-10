package com.example.warehouseapp.model;

import java.time.LocalDate;

public class Employee extends AccountRecordOwner {
    private String name;
    private String surname;
    private String uidNo;
    private EmployeeCredentials employeeCredentials;
    private EmployeeRole role;
    private LocalDate hireDate;
    private LocalDate fireDate;
    private Facility facility;
}