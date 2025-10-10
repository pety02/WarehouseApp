package com.example.warehouseapp.model;

import java.util.List;
public class Supplier extends AccountRecordOwner {
    private String companyName;
    private String companyNo;
    private SupplierLicense license;
    private List<Item> items;
    private List<Package> packages;
    private List<DeliveryOffer> deliveryOffers;
}