package com.order.order.mapper;

import com.order.order.dto.OrderLineRequest;
import com.order.order.dto.OrderLineResponse;
import com.order.order.entities.Order;
import com.order.order.entities.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder().productId(orderLineRequest.
                getProductId()).order(Order.builder().id(orderLineRequest.getOrderId()).build()).
        quantity(orderLineRequest.getQuantity()).build();
    }
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder().orderId(orderLine.getId()).quantity(orderLine.getQuantity()).build();
    }
}
