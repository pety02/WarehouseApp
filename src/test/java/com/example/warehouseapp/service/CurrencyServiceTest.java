package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.CurrencyResponseDTO;
import com.example.warehouseapp.model.entites.Currency;
import com.example.warehouseapp.model.mapper.CurrencyMapper;
import com.example.warehouseapp.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void getCurrencyById_success() {
        UUID id = UUID.randomUUID();
        Currency currency = Currency.builder().id(id).name("USD").build();
        CurrencyResponseDTO dto = CurrencyResponseDTO.builder().id(id.toString()).build();

        when(currencyRepository.findById(id)).thenReturn(Optional.of(currency));
        when(currencyMapper.mapToResponseDTO(currency)).thenReturn(dto);

        CurrencyResponseDTO result = currencyService.getCurrencyById(id);

        assertEquals(id.toString(), result.getId());
    }

    @Test
    void getCurrencyById_notFound() {
        UUID id = UUID.randomUUID();
        when(currencyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> currencyService.getCurrencyById(id));
    }
}
