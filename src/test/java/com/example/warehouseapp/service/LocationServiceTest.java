package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.LocationMapper;
import com.example.warehouseapp.repository.LocationRepository;
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
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;
    @Mock private LocationMapper locationMapper;

    @InjectMocks
    private LocationService service;

    @Test
    void getLocationById_notFound() {
        when(locationRepository.findByIdWithManager(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> service.getLocationById(UUID.randomUUID()));
    }
}
