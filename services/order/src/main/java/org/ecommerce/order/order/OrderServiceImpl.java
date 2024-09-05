package org.ecommerce.order.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ecommerce.order.customer.CustomerClient;
import org.ecommerce.order.exception.BusinessException;
import org.ecommerce.order.kafka.OrderConfirmation;
import org.ecommerce.order.kafka.OrderProducer;
import org.ecommerce.order.orderLine.OrderLineRequest;
import org.ecommerce.order.orderLine.OrderLineService;
import org.ecommerce.order.payment.PaymentClient;
import org.ecommerce.order.payment.PaymentRequest;
import org.ecommerce.order.product.ProductClient;
import org.ecommerce.order.product.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Transactional
    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Cannot create order from an invalid customer id")
        );

        var purhcaseProducts = productClient.purchaseProduct(orderRequest.products());

        var order = this.orderRepository.save(orderMapper.toEntity(orderRequest));

        for(PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purhcaseProducts
                )
        );
        return order.getId();
    }

    @Override
    public List<OrderResponse> listOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponse getOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with id " + orderId + " not found")
                );
    }
}
