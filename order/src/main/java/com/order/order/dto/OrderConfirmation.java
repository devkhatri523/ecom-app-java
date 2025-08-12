package com.order.order.dto;

import com.order.order.entities.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderConfirmation {
    String reference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    CustomerResponse customerResponse;
    List<ProductPuchaseResponse> productPuchaseResponses;
}
