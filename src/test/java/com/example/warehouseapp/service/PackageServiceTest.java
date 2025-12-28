package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.PackageMapper;
import com.example.warehouseapp.repository.PackageRepository;
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
class PackageServiceTest {

    @Mock
    private PackageRepository packageRepository;
    @Mock
    private PackageMapper packageMapper;

    @InjectMocks
    private PackageService service;

    @Test
    void getPackageById_notFound() {
        when(packageRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> service.getPackageById(UUID.randomUUID()));
    }
}
