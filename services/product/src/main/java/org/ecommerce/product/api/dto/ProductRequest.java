package org.ecommerce.product.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull
        String name,
        @NotNull
        String description,
        @Positive
        double availableQuantity,
        @Positive
        BigDecimal price,
        @NotNull
        Integer categoryId
) {

}
