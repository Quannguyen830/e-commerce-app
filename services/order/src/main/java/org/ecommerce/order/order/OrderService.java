package org.ecommerce.order.order;


import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);
    List<OrderResponse> listOrders();
    OrderResponse getOrder(Integer orderId);
}
