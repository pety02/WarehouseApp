package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.model.mapper.TransferMapper;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {
    // Transfer
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    // TransferItem
    private final TransferItemRepository transferItemRepository;
    private final TransferItemMapper transferItemMapper;

    public List<TransferResponseDTO> getAllTransfers() {
        List<Transfer> transfers = transferRepository.findAll();

        if (transfers.isEmpty()) {
            throw new NotFoundEntityException("No transfers found");
        }

        return transfers.stream()
                .map(transfer -> {
                    // Fetch transfer items for this transfer
                    List<TransferItem> transferItems = transferItemRepository.findAllByTransferId(transfer.getId());

                    // Map items to their DTOs (assuming you have a mapper)
                    List<TransferItemResponseDTO> itemDTOs = transferItems.stream()
                            .map(transferItemMapper::mapToResponseDTO)
                            .toList();

                    // Map the transfer itself + its items
                    return transferMapper.mapToResponseDTO(transfer, itemDTOs);
                })
                .toList();
    }

    public TransferResponseDTO getTransferById(UUID id) {
        Transfer transfer = this.transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Transfer not found"));

        // Fetch transfer items for this transfer
        List<TransferItem> transferItems = transferItemRepository.findAllByTransferId(transfer.getId());

        // Map items to their DTOs (assuming you have a mapper)
        List<TransferItemResponseDTO> itemDTOs = transferItems.stream()
                .map(transferItemMapper::mapToResponseDTO)
                .toList();

        // Map the transfer itself + its items
        return transferMapper.mapToResponseDTO(transfer, itemDTOs);
    }

    public List<TransferItemResponseDTO> getAllTransferItems() {
        List<TransferItem> transferItemList = this.transferItemRepository.findAll();
        return transferItemList.stream().map(this.transferItemMapper::mapToResponseDTO).toList();
    }
}