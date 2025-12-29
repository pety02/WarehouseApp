package com.example.warehouseapp.service;

import com.example.warehouseapp.model.dto.StockAdviceCreateRequestDTO;
import com.example.warehouseapp.model.dto.StockAdviceResponseDTO;
import com.example.warehouseapp.model.entites.StockAdvice;
import com.example.warehouseapp.model.mapper.StockAdviceMapper;
import com.example.warehouseapp.repository.StockAdviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockAdviceServiceTest {

    private final StockAdviceRepository repository = Mockito.mock(StockAdviceRepository.class);
    private final StockAdviceMapper mapper = Mockito.mock(StockAdviceMapper.class);
    private final StockAdviceService service = new StockAdviceService(repository, mapper);

    @Test
    void getStockAdviceById_notFound() {
        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                service.getStockAdviceById(id));
    }

    @Test
    void createStockAdvice_success() {
        StockAdviceCreateRequestDTO dto = StockAdviceCreateRequestDTO.builder()
                .validUntil(Instant.now().toString())
                .confidence(0.7)
                .build();

        StockAdvice entity = new StockAdvice();
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);
        Mockito.when(mapper.mapToResponseDTO(entity))
                .thenReturn(StockAdviceResponseDTO.builder().confidence(0.7).build());

        StockAdviceResponseDTO response = service.createStockAdvice(dto);

        assertThat(response.getConfidence()).isEqualTo(0.7);
    }
}
