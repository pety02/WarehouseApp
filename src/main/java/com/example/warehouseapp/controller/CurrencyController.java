package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDTO> getCurrencyById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }
}