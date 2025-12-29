package com.example.warehouseapp.model.mapper;

import com.example.warehouseapp.model.entites.StockAdviceAction;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StockAdviceActionMapper {

    public Map.Entry<String, String> mapToResponseDTO(StockAdviceAction action) {
        return Map.entry(
                action.getId().toString(),
                action.getActionDescription()
        );
    }
}
