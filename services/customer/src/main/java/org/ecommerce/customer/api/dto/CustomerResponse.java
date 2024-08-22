package org.ecommerce.customer.api;

public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
