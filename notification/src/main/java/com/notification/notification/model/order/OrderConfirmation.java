package com.notification.notification.model.order;


import com.notification.notification.model.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
    String reference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    CustomerResponse customerResponse;
    List<ProductPuchaseResponse> productPuchaseResponses;
}
