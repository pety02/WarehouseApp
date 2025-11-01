package com.example.warehouseapp.controller;

import com.example.warehouseapp.model.dto.ItemTypeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/item_types")
public class ItemTypeController {

    @GetMapping
    public ResponseEntity<List<ItemTypeResponseDTO>> getAllItemTypes() {
        // TODO: to implement the logic here
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTypeResponseDTO> getItemTypeById(@PathVariable(name = "id") Long id) {
        // TODO: to implement the logic here
        return null;
    }
}
