package com.order.order.controller;

import com.order.order.dto.OrderReponse;
import com.order.order.dto.OrderRequest;
import com.order.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderReponse>> findAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReponse> findOrderById(@PathVariable("orderId") Integer orderId){
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }
}
