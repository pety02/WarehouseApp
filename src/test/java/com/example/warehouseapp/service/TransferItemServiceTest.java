package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.repository.ItemRepository;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferItemServiceTest {

    @Mock
    private TransferItemRepository transferItemRepository;
    @Mock
    private TransferRepository transferRepository;
    @Mock
    private TransferItemMapper mapper;
    @Mock private ItemRepository itemRepository;

    @InjectMocks
    private TransferItemService service;

    @Test
    void getAllTransferItems_empty_throws() {
        when(transferItemRepository.findAll()).thenReturn(List.of());
        assertThrows(NotFoundEntityException.class, service::getAllTransferItems);
    }

    @Disabled
    @Test
    void createTransferItem_success() {
        UUID transferId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();

        var transfer = mock(com.example.warehouseapp.model.entites.Transfer.class);
        var item = mock(com.example.warehouseapp.model.entites.Item.class);
        var dto = new com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO();
        dto.setItemId(itemId.toString());

        when(transferRepository.findById(transferId)).thenReturn(Optional.of(transfer));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        var entity = mock(com.example.warehouseapp.model.entites.TransferItem.class);
        when(mapper.mapToEntity(dto, transfer, item, "user")).thenReturn(entity);
        when(transferItemRepository.save(entity)).thenReturn(entity);
        var responseDTO = mock(com.example.warehouseapp.model.dto.TransferItemResponseDTO.class);
        when(mapper.mapToResponseDTO(entity)).thenReturn(responseDTO);

        var result = service.createTransferItem(transferId, "user", dto);
        assertSame(responseDTO, result);
    }
}
