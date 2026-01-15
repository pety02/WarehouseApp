package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferItemUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.repository.ItemRepository;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransferItemService {

    private final TransferItemRepository transferItemRepository;
    private final TransferRepository transferRepository;
    private final TransferItemMapper transferItemMapper;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<TransferItemResponseDTO> getAllTransferItems() {
        List<TransferItem> items = transferItemRepository.findAll();

        if (items.isEmpty()) {
            throw new NotFoundEntityException("No transfer items found");
        }

        return items.stream()
                .map(item -> transferItemMapper.mapToResponseDTO(item))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TransferItemResponseDTO> getTransferItemsByTransferId(UUID transferId) {
        if (!transferRepository.existsById(transferId)) {
            throw new NotFoundEntityException("Transfer not found");
        }

        List<TransferItem> items =
                transferItemRepository.findAllByTransfer_Id(transferId);

        if (items.isEmpty()) {
            throw new NotFoundEntityException("No transfer items found for this transfer");
        }

        return items.stream()
                .map(transferItemMapper::mapToResponseDTO)
                .toList();
    }

    public TransferItemResponseDTO createTransferItem(
            UUID transferId,
            String user,
            TransferItemCreateRequestDTO dto
    ) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new NotFoundEntityException("Transfer not found"));

        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new NotFoundEntityException("Item not found"));

        TransferItem transferItem =
                transferItemMapper.toEntity(transfer, item, dto.getQuantity(), user);

        TransferItem savedItem = transferItemRepository.save(transferItem);

        return transferItemMapper.mapToResponseDTO(savedItem);
    }

    public TransferItemResponseDTO updateTransferItem(
            UUID transferItemId,
            TransferItemUpdateRequestDTO dto,
            String user
    ) {
        TransferItem existingItem = transferItemRepository.findById(transferItemId)
                .orElseThrow(() -> new NotFoundEntityException("Transfer item not found"));

        Item item = itemRepository.findById(UUID.fromString(dto.getItemId()))
                .orElseThrow(() -> new NotFoundEntityException("Item not found"));

        transferItemMapper.updateTransferItem(existingItem, item, dto, user);

        TransferItem updatedItem = transferItemRepository.save(existingItem);

        return transferItemMapper.mapToResponseDTO(updatedItem);
    }

    public void deleteTransferItemById(UUID transferItemId) {
        TransferItem transferItem = transferItemRepository.findById(transferItemId)
                .orElseThrow(() -> new NotFoundEntityException("Transfer item not found"));

        if(transferItem.getTransfer() != null) {
            throw new IllegalStateException("Transfer item is connected with a transfer. So, it cannot be deleted");
        }
        if(transferItem.getItem() != null) {
            throw  new IllegalStateException("Item is connected with an item. So, it cannot be deleted");
        }

        transferItemRepository.delete(transferItem);
    }
}