package org.ecommerce.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest paymentRequest
    ) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }
}
