package org.ecommerce.customer.api;

import org.ecommerce.customer.api.dto.CustomerRequest;
import org.ecommerce.customer.api.dto.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest customerRequest) {
        if(customerRequest == null) {
            return null;
        }

        return Customer.builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();
    }

    public CustomerResponse toDto(Customer customer) {
        if(customer == null) {
            return null;
        }

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
