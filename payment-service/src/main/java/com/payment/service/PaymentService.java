package com.payment.service;

import com.payment.dto.PaymentRequest;
import com.payment.entity.Payment;
import com.payment.mapper.PaymentMapper;
import com.payment.notification.PaymentNotificationProducer;
import com.payment.notification.PaymentNotificationRequest;
import com.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private PaymentNotificationProducer paymentNotificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        paymentNotificationProducer.sendPaymentNotification(PaymentNotificationRequest.builder()
                .orderReference(paymentRequest.getOrderRefernece())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .customerFirstName(paymentRequest.getCustomer().getFirstName())
                .customerLastName(paymentRequest.getCustomer().getLastName())
                .customerEmail(paymentRequest.getCustomer().getEmail())

                .build());
        return payment.getId();
    }


}
