package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Transfer> create(
            @RequestBody @Valid TransferCreateRequestDTO dto,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(
                transferService.createTransfer(dto, principal.getName())
        );
    }

    @GetMapping
    public List<Transfer> getAll() {
        return transferService.getAllTransfers();
    }

    @GetMapping("/{id}")
    public Transfer getById(@PathVariable UUID id) {
        return transferService.getTransferById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
