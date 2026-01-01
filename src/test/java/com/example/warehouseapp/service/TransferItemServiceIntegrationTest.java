package com.example.warehouseapp.service;

import com.example.warehouseapp.repository.ItemRepository;
import com.example.warehouseapp.repository.TransferItemRepository;
import com.example.warehouseapp.repository.TransferRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class TransferItemServiceIntegrationTest {

    @Autowired
    private TransferItemService service;
    @Autowired
    private TransferItemRepository repository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Disabled
    @Test
    void createAndFetchTransferItem_realDb() {
        var transfer = transferRepository.save(new com.example.warehouseapp.model.entites.Transfer());
        var item = itemRepository.save(new com.example.warehouseapp.model.entites.Item());
        var dto = new com.example.warehouseapp.model.dto.TransferItemCreateRequestDTO();
        dto.setItemId(item.getId().toString());

        var response = service.createTransferItem(transfer.getId(), "user", dto);
        assertNotNull(response);
    }
}
