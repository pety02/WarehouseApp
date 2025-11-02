package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.mapper.CurrencyMapper;
import com.example.warehouseapp.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Transactional(readOnly=true)
    public CurrencyResponseDTO getCurrencyById(UUID id) {
        Optional<Currency> currency = this.currencyRepository.findById(id);
        if(currency.isEmpty()) {
            throw new NotFoundEntityException("Currency not found.");
        }
        return this.currencyMapper.mapToResponseDTO(currency.get());
    }
}
