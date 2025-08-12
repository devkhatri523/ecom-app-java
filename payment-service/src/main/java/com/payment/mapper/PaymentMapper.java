package com.payment.mapper;


import com.payment.dto.PaymentRequest;
import com.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest){
        if(paymentRequest == null){
            return null;
        }
        return Payment.builder().paymentMethod(paymentRequest.getPaymentMethod()).
                amount(paymentRequest.getAmount()).
                orderId(paymentRequest.getOrderId()).
                build();

    }
}
