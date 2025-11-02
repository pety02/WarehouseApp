package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {
    public CurrencyResponseDTO mapToResponseDTO(Currency currency) {
        CurrencyResponseDTO responseDTO = new CurrencyResponseDTO();
        responseDTO.setId(currency.getId().toString());
        responseDTO.setAbbreviation(currency.getAbbreviation());
        currency.setName(currency.getName());

        return responseDTO;
    }
}
