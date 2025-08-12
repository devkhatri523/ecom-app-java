package com.order.order.service;

import com.order.order.client.CustomerClient;
import com.order.order.client.PaymentClient;
import com.order.order.client.ProductClient;
import com.order.order.dto.*;
import com.order.order.exception.BusinessException;
import com.order.order.mapper.OrderMapper;
import com.order.order.notification.OrderNotificationProducer;
import com.order.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private CustomerClient customerClient;
    private ProductClient productClient;
    private PaymentClient paymentClient;
    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private OrderLineService orderLineService;
    private final OrderNotificationProducer orderNotificationProducer;

    public Integer createOrder(OrderRequest orderRequest) {
        CustomerResponse customer = this.customerClient.getCustomerInfo(orderRequest.getCustomerId());
        if(customer == null){
            throw  new BusinessException("Cannot create order :: No customer exists found");
        }
        var purchaseProducts = this.productClient.purchaseProducts(orderRequest.getPurchaseRequests().stream()
                .map(orderMapper::toProductPurchaseRequest).toList());
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));
        for(PurchaseRequest purchaseRequest : orderRequest.getPurchaseRequests()){
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder().orderId(order.getId()).
                            productId(purchaseRequest.getProductId()).quantity(purchaseRequest.getQuantity()).build()
            );
        }
        var paymentRequest = PaymentRequest.builder().orderId(order.getId()).
                amount(orderRequest.getAmount()).paymentMethod(orderRequest.getPaymentMethod())
                .orderRefernece(orderRequest.getReference())
                .customer(customer).build();
        this.paymentClient.createPayment(paymentRequest);
        orderNotificationProducer.sendPaymentNotification(
                OrderConfirmation.builder().reference(orderRequest.getReference()).totalAmount(orderRequest.getAmount())
                        .customerResponse(customer).paymentMethod(orderRequest.getPaymentMethod())
                        .productPuchaseResponses(purchaseProducts)
                        .build()

        );
        return order.getId();

    }
    public List<OrderReponse> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }
    public OrderReponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId).map(orderMapper::toOrderResponse).orElseThrow(
                ()->new EntityNotFoundException(String.format("Order with id :  %d not found",orderId)));
    }


}
