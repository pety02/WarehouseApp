package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMapperTest {

    private CurrencyMapper currencyMapper;

    @BeforeEach
    void setUp() {
        currencyMapper = new CurrencyMapper();
    }

    @Test
    void mapToResponseDTO_shouldMapAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();

        Currency currency = new Currency();
        currency.setId(id);
        currency.setName("Euro");
        currency.setAbbreviation("EUR");

        CurrencyResponseDTO result =
                currencyMapper.mapToResponseDTO(currency);

        assertNotNull(result);
        assertEquals(id.toString(), result.getId());
        assertEquals("Euro", result.getName());
        assertEquals("EUR", result.getAbbreviation());
    }

    @Test
    void mapToResponseDTO_shouldNotModifySourceEntity() {
        UUID id = UUID.randomUUID();

        Currency currency = new Currency();
        currency.setId(id);
        currency.setName("Bulgarian Lev");
        currency.setAbbreviation("BGN");

        currencyMapper.mapToResponseDTO(currency);

        assertEquals(id, currency.getId());
        assertEquals("Bulgarian Lev", currency.getName());
        assertEquals("BGN", currency.getAbbreviation());
    }

    @Test
    void mapToResponseDTO_withNullCurrency_shouldThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> currencyMapper.mapToResponseDTO(null),
                "Passing null currency should throw NullPointerException"
        );
    }
}
