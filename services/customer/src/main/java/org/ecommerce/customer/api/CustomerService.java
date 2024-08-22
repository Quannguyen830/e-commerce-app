package org.ecommerce.customer.customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest customerRequest);
    void updateCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> listCustomers();
    CustomerResponse getCustomer(String customerId);
    boolean existByCustomerId(String customerId);
    void deleteCustomer(String customerId);
}
