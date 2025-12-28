package com.example.warehouseapp.service;

import com.example.warehouseapp.model.mapper.StockAdviceMapper;
import com.example.warehouseapp.repository.StockAdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockAdviceService {
    // TODO: should be implemented and tested
    private final StockAdviceRepository stockAdviceRepository;
    private final StockAdviceMapper stockAdviceMapper;
}
