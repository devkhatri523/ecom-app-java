package com.payment.notification;

import com.payment.dto.PaymentRequest;
import com.payment.model.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentNotificationRequest {
    private String orderReference;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    String customerFirstName;
    String customerLastName;
    String customerEmail;
}
