package com.order.order.dto;


import com.order.order.entities.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequest {
    private String id;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Integer orderId;
    String orderRefernece;
    CustomerResponse customer;

}
