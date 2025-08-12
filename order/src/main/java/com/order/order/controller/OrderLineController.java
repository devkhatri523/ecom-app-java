package com.order.order.controller;

import com.order.order.dto.OrderLineResponse;
import com.order.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findOrderLineByOrderId(@PathVariable("orderId") Integer orderId){
        return ResponseEntity.ok(orderLineService.findAllOrderLineByOrderId(orderId));
    }

}
