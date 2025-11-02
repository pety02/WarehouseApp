package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.mapper.CurrencyMapper;
import com.example.warehouseapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }

    public CurrencyResponseDTO getCurrencyById(UUID id) {
        Optional<Currency> currency = this.currencyRepository.findById(id);
        if(currency.isEmpty()) {
            throw new NotFoundEntityException("Currency not found.");
        }
        return this.currencyMapper.mapToResponseDTO(currency.get());
    }
}
