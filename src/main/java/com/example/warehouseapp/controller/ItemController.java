package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.ItemResponseDTO;
import com.example.warehouseapp.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(
            @PathVariable UUID id) {

        try {
            return ResponseEntity.ok(
                    itemService.getItemById(id)
            );
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
