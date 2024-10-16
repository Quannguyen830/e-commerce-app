package org.ecommerce.order.orderLine;

import org.ecommerce.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toEntity(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .build();
    }

    public OrderLineResponse toDto(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(), orderLine.getQuantity()
        );
    }
}
