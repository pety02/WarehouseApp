package com.example.warehouseapp.service;

import com.example.warehouseapp.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class TransferServiceIntegrationTest {

    @Autowired
    private TransferService service;
    @Autowired
    private TransferRepository repository;

    @Test
    void createAndFetchTransfer() {
        var transfer = repository.save(new com.example.warehouseapp.model.entites.Transfer());
        var dto = service.getTransferById(transfer.getId());
        assertNotNull(dto);
    }
}
