package com.example.warehouseapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee extends AccountRecordOwner {
    private String name;
    private String surname;
    private String uidNo;
    private EmployeeCredentials employeeCredentials;
    private EmployeeRole role;
    private LocalDate hireDate;
    private LocalDate fireDate;
    private Facility facility;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}