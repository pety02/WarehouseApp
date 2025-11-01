package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> getAllTransfers() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> getTransferById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDTO> createTransfer(@RequestBody TransferCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> updateTransferById(@PathVariable(name = "id") Long id,
                                                                  @RequestBody TransferUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTransferById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
    }

    @GetMapping("/{id}/transfer_items")
    public ResponseEntity<List<TransferItemResponseDTO>> getAllTransferItems(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }

    @PostMapping("/{id}/transfer_items")
    public ResponseEntity<TransferItemResponseDTO> createTransferItem(@PathVariable(name = "id") Long id,
                                                                      @RequestBody TransferItemCreateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @PutMapping("/{id}/transfer_items/{tiid}")
    public ResponseEntity<TransferItemResponseDTO> updateTransferItemById(@PathVariable(name = "id") Long id,
                                                                          @PathVariable(name = "tiid") Long tiid,
                                                                          @RequestBody TransferItemUpdateRequestDTO obj) {
        // TODO: to implement the logic here
        return null;
    }

    @DeleteMapping("/{id}/transfer_items/{tiid}")
    public void deleteTransferItemById(@PathVariable(name = "id") Long id, @PathVariable(name = "tiid") Long tiid) {
        // TODO: to implement the logic here
    }
}