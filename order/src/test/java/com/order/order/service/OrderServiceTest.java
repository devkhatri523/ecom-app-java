package com.order.order.service;

import com.order.order.client.CustomerClient;
import com.order.order.client.PaymentClient;
import com.order.order.client.ProductClient;
import com.order.order.dto.*;
import com.order.order.entities.Order;
import com.order.order.mapper.OrderMapper;
import com.order.order.notification.OrderNotificationProducer;
import com.order.order.repository.OrderRepository;
import com.order.order.testdata.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private CustomerClient customerClient;
    @Mock
    private ProductClient productClient;
    @Mock
    private PaymentClient paymentClient;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderLineService orderLineService;
    @Mock
    private OrderNotificationProducer orderNotificationProducer;


    @InjectMocks
    private OrderService orderService;

    private OrderRequest orderRequest;

    private CustomerResponse customerResponse;
    private ProductPuchaseResponse productPuchaseResponse;


    private Order order;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderRequest = TestUtil.createOrderRequest();
        customerResponse = TestUtil.createCustomerResponse();
        productPuchaseResponse = TestUtil.createProductPuchaseResponse();
        order = TestUtil.createOrder();
    }

    @Test
    public void testCreateOrder() {
        Mockito.when(customerClient.getCustomerInfo("CUST-1")).thenReturn(customerResponse);
        Mockito.doReturn(List.of(productPuchaseResponse))
                .when(productClient)
                .purchaseProducts(anyList());
        Mockito.when(orderLineService.saveOrderLine(any(OrderLineRequest.class))).thenReturn(1);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.lenient().doNothing().when(orderNotificationProducer).sendPaymentNotification(any(OrderConfirmation.class));
        Mockito.when(orderMapper.toOrder(orderRequest)).thenReturn(order);

        Integer result = orderService.createOrder(orderRequest);

        assertEquals(10,result);
        verify(customerClient,times(1)).getCustomerInfo("CUST-1");
        verify(productClient,times(1)).purchaseProducts(anyList());
        verify(orderLineService,times(1)).saveOrderLine(any(OrderLineRequest.class));
        verify(orderRepository,times(1)).save(order);


    }

    @Test
    public void testFindAllOrders(){
        when(orderRepository.findAll()).thenReturn(List.of(order));
        List<OrderReponse> results = orderService.findAllOrders();
        assertEquals(1,results.size());

    }


}
