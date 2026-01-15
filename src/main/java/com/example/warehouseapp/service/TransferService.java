package com.example.warehouseapp.service;

import com.example.warehouseapp.exception.NotFoundEntityException;
import com.example.warehouseapp.model.dto.TransferCreateRequestDTO;
import com.example.warehouseapp.model.dto.TransferResponseDTO;
import com.example.warehouseapp.model.entites.Item;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.model.entites.TransferItem;
import com.example.warehouseapp.model.mapper.TransferItemMapper;
import com.example.warehouseapp.model.mapper.TransferMapper;
import com.example.warehouseapp.repository.ItemRepository;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransferService {

    private final TransferRepository transferRepository;
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;
    private final TransferMapper transferMapper;
    private final TransferItemMapper transferItemMapper;

    public TransferResponseDTO createTransfer(
            TransferCreateRequestDTO dto,
            String user
    ) {

        Location source = locationRepository.findById(dto.getSourceLocationId())
                .orElseThrow(() -> new NotFoundEntityException("Source location not found"));

        Location destination = locationRepository.findById(dto.getDestinationLocationId())
                .orElseThrow(() -> new NotFoundEntityException("Destination location not found"));

        Transfer transfer = transferMapper.toEntity(dto, source, destination, user);

        List<TransferItem> items = dto.getItems().stream().map(i -> {
            Item item = itemRepository.findById(i.getItemId())
                    .orElseThrow(() -> new NotFoundEntityException("Item not found"));

            return transferItemMapper.toEntity(
                    transfer,
                    item,
                    i.getQuantity(),
                    user
            );
        }).toList();

        transfer.setItems(items);

        return this.transferMapper.toDTO(transferRepository.save(transfer));
    }

    public List<TransferResponseDTO> getAllTransfers() {
        return transferRepository.findAll().stream().map(this.transferMapper::toDTO).toList();
    }

    public TransferResponseDTO getTransferById(UUID id) {
        return this.transferMapper.toDTO(transferRepository.findByIdWithLocations(id)
                .orElseThrow(() -> new NotFoundEntityException("Transfer not found")));
    }

    public void deleteTransfer(UUID id) {
        Transfer transfer = transferRepository
                .findById(id).orElseThrow(() -> new NotFoundEntityException("Transfer not found"));
        if(!transfer.getItems().isEmpty()) {
            throw new IllegalStateException("Transfer's items not empty list. So, the transfer cannot be deleted");
        }
        if(transfer.getSourceLocation() != null || transfer.getDestinationLocation() != null) {
            throw new IllegalStateException("Transfer has a source or destination location attached to it. " +
                    "So, the transfer cannot be deleted");
        }

        transferRepository.deleteById(id);
    }
}
