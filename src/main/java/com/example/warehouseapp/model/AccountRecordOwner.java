package com.example.warehouseapp.model;

import java.util.List;
public class AccountRecordOwner {
    private Long id;
    private List<AccountRecord> accountRecords;
    private List<Order> benefactorOfOrders;
    private List<Order> recipientOfOrders;
}