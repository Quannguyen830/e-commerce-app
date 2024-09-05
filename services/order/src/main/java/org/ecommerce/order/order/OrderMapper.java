package org.ecommerce.order.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toEntity(OrderRequest orderRequest) {
        if(orderRequest == null) return null;
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .paymentMethod(orderRequest.paymentMethod())
                .reference(orderRequest.reference())
                .build();
    }

    public OrderResponse toDto(Order order) {
        if(order == null) return null;
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
