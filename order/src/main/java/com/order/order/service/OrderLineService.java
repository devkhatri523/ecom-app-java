package com.order.order.service;

import com.order.order.dto.OrderLineRequest;
import com.order.order.dto.OrderLineResponse;
import com.order.order.entities.OrderLine;
import com.order.order.mapper.OrderLineMapper;
import com.order.order.repository.OrderLineRepository;
import com.order.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLine) {
        return orderLineRepository.save(orderLineMapper.toOrderLine(orderLine)).getId();
    }

    public List<OrderLineResponse> findAllOrderLineByOrderId(Integer orderId){
        return orderLineRepository.findAllByOrderId(orderId).stream().map(orderLineMapper::toOrderLineResponse).toList();
    }
}
