package com.example.warehouseapp.model;

import java.time.LocalDate;
import java.util.List;
public class SupplierLicense {
    private Long id;
    private Supplier supplier;
    private String supplierName;
    private String licenseNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String issuedBy;
    private boolean haccpCertified;
    private boolean coldChainCertified;
    private boolean organicCertified;
    private List<String> permittedProductCategories;
    private List<String> certificateDocuments;
    private boolean lastInspectionPassed;
    private LocalDate lastInspectionDate;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
}