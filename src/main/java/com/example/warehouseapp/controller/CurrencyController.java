package com.example.warehouseapp.controller;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDTO> getCurrencyById(@PathVariable(name = "id") Long id) {
        CurrencyResponseDTO responseDTO;

        try {
             responseDTO = this.currencyService.getCurrencyById(id);
        } catch (NotFoundEntityException ex) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseDTO);
    }
}