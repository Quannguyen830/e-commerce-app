package org.ecommerce.payment.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toEntity(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .build();
    }
}
