package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public CurrencyResponseDTO mapToResponseDTO(Currency currency) {
        return CurrencyResponseDTO
                .builder()
                .id(currency.getId().toString())
                .name(currency.getName())
                .abbreviation(currency.getAbbreviation())
                .build();
    }
}
