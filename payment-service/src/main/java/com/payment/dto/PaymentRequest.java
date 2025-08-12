package com.payment.dto;

import com.payment.model.PaymentMethod;
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
    Customer customer;

}
