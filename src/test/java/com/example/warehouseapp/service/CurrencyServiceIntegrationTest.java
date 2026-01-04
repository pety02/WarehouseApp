package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.repository.CurrencyRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@Transactional
class CurrencyServiceIntegrationTest {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void getCurrencyById_realDb() {
        Currency currency = currencyRepository.save(
                Currency.builder().name("EUR").abbreviation("EUR").build()
        );

        CurrencyResponseDTO dto =
                currencyService.getCurrencyById(currency.getId());

        assertEquals(currency.getId().toString(), dto.getId());
    }
}
