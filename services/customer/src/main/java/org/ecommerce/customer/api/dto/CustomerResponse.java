package org.ecommerce.customer.api.dto;

import org.ecommerce.customer.api.Address;

public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
