package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.LowStockAlertCreateRequestDTO;
import com.example.warehouseapp.model.dto.LowStockAlertResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/low_stock_alerts")
public class LowStockAlertController {

    @GetMapping
    public ResponseEntity<List<LowStockAlertResponseDTO>> getAllLowStockAlerts() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LowStockAlertResponseDTO> getLowStockAlertById(@PathVariable(name = "id") Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<LowStockAlertResponseDTO> createLowStockAlert(@RequestBody LowStockAlertCreateRequestDTO obj) {
        return null;
    }
}
