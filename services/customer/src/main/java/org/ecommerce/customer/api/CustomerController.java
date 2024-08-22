package org.ecommerce.customer.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.customer.api.dto.CustomerRequest;
import org.ecommerce.customer.api.dto.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listCustomers() {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.existByCustomerId(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
