package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.model.mapper.TransferMapper;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository repository;
    @Mock
    private TransferItemRepository itemRepository;
    @Mock
    private TransferMapper mapper;
    @Mock
    private TransferItemMapper itemMapper;
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private TransferService service;

    @Test
    void getAllTransfers_empty_throws() {
        when(repository.findAll()).thenReturn(List.of());
        assertThrows(NotFoundEntityException.class, service::getAllTransfers);
    }

    @Test
    void getTransferById_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundEntityException.class, () -> service.getTransferById(id));
    }
}
