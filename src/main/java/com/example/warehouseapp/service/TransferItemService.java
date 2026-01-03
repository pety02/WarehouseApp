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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferItemService {

    private final TransferItemRepository transferItemRepository;
    private final TransferRepository transferRepository;
    private final TransferItemMapper transferItemMapper;
    private final ItemRepository itemRepository;

    public List<TransferItemResponseDTO> getAllTransferItems() {
        List<TransferItem> items = transferItemRepository.findAll();

        if (items.isEmpty()) {
            throw new NotFoundEntityException("No transfer items found");
        }

        return items.stream()
                .map(transferItemMapper::mapToResponseDTO)
                .toList();
    }

    public List<TransferItemResponseDTO> getTransferItemsByTransferId(UUID transferId) {
        transferRepository.findById(transferId)
                .orElseThrow(() -> new NotFoundEntityException("Transfer not found"));

        List<TransferItem> items =
                transferItemRepository.findAllByTransferId(transferId);

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

        Item item = itemRepository.findById(UUID.fromString(dto.getItemId()))
                .orElseThrow(() -> new NotFoundEntityException("Item not found"));

        TransferItem transferItem =
                transferItemMapper.mapToEntity(dto, transfer, item, user);

        TransferItem savedItem =
                transferItemRepository.save(transferItem);

        return transferItemMapper.mapToResponseDTO(savedItem);
    }

    public TransferItemResponseDTO updateTransferItem(
            UUID transferItemId,
            TransferItemUpdateRequestDTO dto,
            String user
    ) {
        TransferItem existingItem = transferItemRepository.findById(transferItemId)
                .orElseThrow(() -> new NotFoundEntityException("Transfer item not found"));

        Item item = existingItem.getItem();

        transferItemMapper.updateTransferItem(existingItem, item, dto, user);

        TransferItem updatedItem =
                transferItemRepository.save(existingItem);

        return transferItemMapper.mapToResponseDTO(updatedItem);
    }

    public void deleteTransferItemById(UUID transferItemId) {
        if (!transferItemRepository.existsById(transferItemId)) {
            throw new NotFoundEntityException("Transfer item not found");
        }

        transferItemRepository.deleteById(transferItemId);
    }
}
