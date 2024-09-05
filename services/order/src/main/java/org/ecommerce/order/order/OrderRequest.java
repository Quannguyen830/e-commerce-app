package org.ecommerce.order.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderRequest(
        Integer id,
        String reference,
        @Positive
        BigDecimal amount,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        @NotEmpty
        @NotBlank
        String customerId,
        @NotEmpty
        List<PurchaseRequest> products
) {

}
