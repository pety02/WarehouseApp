package com.example.warehouseapp.repository;

import com.example.warehouseapp.model.entites.TransferItem;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Disabled
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false"
})
class TransferItemRepositoryTest {

    @Autowired
    private TransferItemRepository repository;

    @Disabled
    @Test
    void findAllByIds_success() {
        TransferItem t1 = repository.save(new TransferItem());
        TransferItem t2 = repository.save(new TransferItem());

        List<TransferItem> result =
                repository.findAllByIds(List.of(t1.getId(), t2.getId()));

        assertEquals(2, result.size());
    }
}
