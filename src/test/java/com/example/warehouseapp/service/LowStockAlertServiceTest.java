package com.example.warehouseapp.service;

import ch.qos.logback.core.net.server.Client;
import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.LowStockAlertMapper;
import com.example.warehouseapp.model.mapper.StockAvailabilityMapper;
import com.example.warehouseapp.repository.LowStockAlertRepository;
import com.example.warehouseapp.repository.StockAvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LowStockAlertServiceTest {

    @Mock
    private Client client;
    @Mock
    private LowStockAlertRepository lowStockAlertRepository;
    @Mock
    private StockAvailabilityRepository stockAvailabilityRepository;
    @Mock
    private StockAvailabilityMapper stockAvailabilityMapper;
    @Mock
    private LowStockAlertMapper lowStockAlertMapper;

    @InjectMocks
    private LowStockAlertService service;

    @Test
    void getLowStockAlertById_notFound() {
        when(lowStockAlertRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> service.getLowStockAlertById(UUID.randomUUID()));
    }
}
