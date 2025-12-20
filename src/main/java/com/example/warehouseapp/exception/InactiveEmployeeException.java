package com.example.warehouseapp.exception;

public class InactiveEmployeeException extends RuntimeException {
    public InactiveEmployeeException(String message) {
        super(message);
    }
}
