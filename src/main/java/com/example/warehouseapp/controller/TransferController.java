package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;


    @PostMapping
    public ResponseEntity<TransferResponseDTO> create(
            @RequestBody @Valid TransferCreateRequestDTO dto,
            @AuthenticationPrincipal Principal principal
    ) {
        try {
            TransferResponseDTO response =
                    transferService.createTransfer(dto, principal.getName());

            URI location = URI.create("/transfers/" + response.getId());

            return ResponseEntity
                    .created(location)
                    .body(response);

        } catch (Exception ex) {
            log.error("Occurred exception: {}", ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAll() {
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(transferService.getTransferById(id));
        } catch (Exception ex) {
            log.error("Occurred exception: %s".formatted(ex.getMessage()), ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            transferService.deleteTransfer(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            log.error("Occurred exception: %s".formatted(ex.getMessage()), ex);
            return ResponseEntity.badRequest().build();
        }
    }
}
