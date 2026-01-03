package com.example.warehouseapp.service;

import com.example.warehouseapp.model.entites.Address;
import com.example.warehouseapp.model.entites.Location;
import com.example.warehouseapp.model.entites.Transfer;
import com.example.warehouseapp.repository.LocationRepository;
import com.example.warehouseapp.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class TransferServiceIntegrationTest {

    @Autowired
    private TransferService service;

    @Autowired
    private TransferRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void createAndFetchTransfer() {
        Address address = Address.builder()
                .city("Sofia")
                .country("Bulgaria")
                .street("Main St")
                .no("1")
                .zip("1000")
                .build();

        Location source = locationRepository.save(
                Location.builder()
                        .name("Source")
                        .address(address)
                        .build()
        );

        Location destination = locationRepository.save(
                Location.builder()
                        .name("Destination")
                        .address(address)
                        .build()
        );

        Transfer transfer = Transfer.builder()
                .deliveryDateTime(Instant.now())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .sourceLocation(source)
                .destinationLocation(destination)
                .remarks("Test transfer")
                .build();

        transfer = repository.save(transfer);

        var dto = service.getTransferById(transfer.getId());

        assertNotNull(dto);
    }
}
