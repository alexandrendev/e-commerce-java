package com.alexandre.eCommerce.Domain.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductEntryRequest(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @Min(1)
        int quantity
) {
}
