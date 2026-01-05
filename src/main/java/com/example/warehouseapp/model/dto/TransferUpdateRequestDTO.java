package com.example.warehouseapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferUpdateRequestDTO {

    @NotNull(message = "Delivery date and time is required")
    @FutureOrPresent(message = "Delivery date and time cannot be in the past")
    private Instant deliveryDateTime;

    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;

    @NotNull(message = "Transfer items are required")
    @Size(min = 1, message = "At least one transfer item is required")
    private List<
            @NotBlank(message = "Transfer item must not be blank")
                    String
            > transferItems;

    @NotBlank(message = "Source location is required")
    @Size(max = 100, message = "Source location must not exceed 100 characters")
    private String sourceLocation;

    @NotBlank(message = "Destination location is required")
    @Size(max = 100, message = "Destination location must not exceed 100 characters")
    private String destinationLocation;
}
