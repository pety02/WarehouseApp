package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferItemResponseDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.dto.TransferUpdateRequestDTO;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.model.mapper.TransferMapper;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {
    // Transfer
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final LocationRepository locationRepository;

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

    public TransferResponseDTO createTransfer(TransferCreateRequestDTO transferRequestDTO, String user) {
        LocalDate today = LocalDate.now();

        List<TransferItem> items = transferItemRepository.findAllByIds(transferRequestDTO.getTransferItems().stream().map(UUID::fromString).toList());

        Location sourceLocation = locationRepository.findByIdWithManager(UUID.fromString(transferRequestDTO.getSourceLocation()))
                .orElseThrow(() -> new NotFoundEntityException("Source location not found"));

        Location destinationLocation = locationRepository.findByIdWithManager(UUID.fromString(transferRequestDTO.getDestinationLocation()))
                .orElseThrow(() -> new NotFoundEntityException("Destination location not found"));

        Transfer transfer = transferMapper.mapToEntity(transferRequestDTO, user,
                today, items, sourceLocation, destinationLocation);

        Transfer savedTransfer = transferRepository.save(transfer);

        List<TransferItem> transferItems =
                transferItemRepository.findAllByTransferId(savedTransfer.getId());

        List<TransferItemResponseDTO> itemDTOs = transferItems.stream()
                .map(transferItemMapper::mapToResponseDTO)
                .toList();

        return transferMapper.mapToResponseDTO(savedTransfer, itemDTOs);
    }

    public TransferResponseDTO updateTransfer(UUID id, TransferUpdateRequestDTO transferRequestDTO, String user) {
        LocalDate today = LocalDate.now();

        List<TransferItem> items = transferItemRepository.findAllByIds(transferRequestDTO.getTransferItems().stream().map(UUID::fromString).toList());

        Location sourceLocation = locationRepository.findByIdWithManager(UUID.fromString(transferRequestDTO.getSourceLocation()))
                .orElseThrow(() -> new NotFoundEntityException("Source location not found"));

        Location destinationLocation = locationRepository.findByIdWithManager(UUID.fromString(transferRequestDTO.getDestinationLocation()))
                .orElseThrow(() -> new NotFoundEntityException("Destination location not found"));

        // Fetch existing transfer
        Transfer existingTransfer = transferRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Transfer not found"));

        // Update entity fields
        transferMapper.updateTransfer(existingTransfer, transferRequestDTO, user, today, items, sourceLocation, destinationLocation);

        // Persist updated transfer
        Transfer updatedTransfer = transferRepository.save(existingTransfer);

        // Fetch updated transfer items
        List<TransferItem> transferItems =
                transferItemRepository.findAllByTransferId(updatedTransfer.getId());

        // Map items → DTOs
        List<TransferItemResponseDTO> itemDTOs = transferItems.stream()
                .map(transferItemMapper::mapToResponseDTO)
                .toList();

        // Map transfer + items → response DTO
        return transferMapper.mapToResponseDTO(updatedTransfer, itemDTOs);
    }

    public void deleteTransferById(UUID id) {
        transferRepository.deleteById(id);
    }
}
