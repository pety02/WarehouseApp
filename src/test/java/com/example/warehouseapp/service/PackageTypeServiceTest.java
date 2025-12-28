package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.PackageTypeMapper;
import com.example.warehouseapp.repository.PackageTypeRepository;
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
class PackageTypeServiceTest {

    @Mock
    private PackageTypeRepository repository;
    @Mock
    private PackageTypeMapper mapper;

    @InjectMocks
    private PackageTypeService service;

    @Test
    void getPackageTypeById_notFound() {
        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> service.getPackageTypeById(UUID.randomUUID()));
    }
}
