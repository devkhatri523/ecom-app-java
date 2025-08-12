package com.order.order.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.order.dto.OrderReponse;
import com.order.order.dto.OrderRequest;
import com.order.order.service.OrderService;
import com.order.order.testdata.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    private OrderRequest orderRequest;
    private OrderReponse orderReponse;
    @BeforeEach
    void setUp() {
        orderRequest = TestUtil.createOrderRequest();
        orderReponse = TestUtil.createOrderResponse();
    }

    @Test
    public void testCreateOrder() throws Exception{
        Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class))).thenReturn(1);
        mockMvc.perform(post("/api/v1/orders").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest))).
                andExpect(status().isOk()).andExpect(content().string("1"));
    }

    @Test
    public void testFindAllOrders() throws Exception{
        Mockito.when(orderService.findAllOrders()).thenReturn(List.of(orderReponse));
        mockMvc.perform(get("/api/v1/orders").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.size()").value(1)).
                andExpect(jsonPath("$[0].customerId").value("CUST-1"))
                .andExpect(jsonPath("$[0].amount").value("200"));
    }

    @Test
    public void testFindOrderById() throws Exception{
        Mockito.when(orderService.findOrderById(1)).thenReturn(orderReponse);
        mockMvc.perform(get("/api/v1/orders/{orderId}",1).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.customerId").value("CUST-1"))
                .andExpect(jsonPath("$.amount").value("200"));
    }


}
