package org.ecommerce.order.payment;

import org.ecommerce.order.customer.CustomerResponse;
import org.ecommerce.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customerResponse
) {
}
