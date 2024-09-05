package org.ecommerce.order.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        return orderLineRepository.save(orderLineMapper.toEntity(orderLineRequest)).getId();
    }

    public List<OrderLineResponse> listOrderLines(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toDto)
                .toList();
    }
}
