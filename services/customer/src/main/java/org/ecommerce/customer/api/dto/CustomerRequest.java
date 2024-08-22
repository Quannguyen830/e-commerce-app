package org.ecommerce.customer.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.ecommerce.customer.api.Address;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstName,
        @NotNull(message = "Customer lastname is required")
        String lastName,
        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is not a valid email address")
        String email,
        Address address
) {

}
