package com.example.warehouseapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock_advices")
@RequiredArgsConstructor
@Tag(
        name = "Stock Advices",
        description = "Stock Advices management endpoints (CRUD, authentication)"
)
public class StockAdviceController {
    // TODO: should be implemented and tested
}
