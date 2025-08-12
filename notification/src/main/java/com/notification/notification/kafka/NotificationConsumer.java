package com.notification.notification.kafka;

import com.notification.notification.constant.NotificationType;
import com.notification.notification.entity.Notification;
import com.notification.notification.model.order.OrderConfirmation;
import com.notification.notification.model.payment.PaymentConfirmation;
import com.notification.notification.repository.NotificationRepository;
import com.notification.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentInformation(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Received Payment Confirmation for notification {}", paymentConfirmation);
        notificationRepository.save(Notification.builder().type(NotificationType.PAYMENT_CONFIRMATION).notificationDate(LocalDateTime.now())
                .paymentConfirmation(PaymentConfirmation.builder().orderReference(String.valueOf(paymentConfirmation.getOrderReference()))
                        .amount(paymentConfirmation.getAmount()).customerFirstName(paymentConfirmation.getCustomerFirstName())
                        .customerLastName(paymentConfirmation.getCustomerLastName()).customerEmail(paymentConfirmation.getCustomerEmail())
                        .paymentMethod(paymentConfirmation.getPaymentMethod()).build()).build());
        String customerName = paymentConfirmation.getCustomerFirstName() + " "+paymentConfirmation.getCustomerLastName();
        emailService.sendPaymentSucessFullEmail(paymentConfirmation.getCustomerEmail(),customerName,
                paymentConfirmation.getAmount(), paymentConfirmation.getOrderReference());


    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderInformation(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Received Order Confirmation for notification {}", orderConfirmation);
        notificationRepository.save(Notification.builder().type(NotificationType.ORDER_CONFIRMATION).notificationDate(LocalDateTime.now())
                .paymentConfirmation(PaymentConfirmation.builder().orderReference(String.valueOf(orderConfirmation.getReference()))
                        .amount(orderConfirmation.getTotalAmount()).customerFirstName(orderConfirmation.getCustomerResponse().getFirstName())
                        .customerLastName(orderConfirmation.getCustomerResponse().getLastName()).customerEmail(orderConfirmation.getCustomerResponse().getEmail())
                        .paymentMethod(orderConfirmation.getPaymentMethod()).build()).build());
        String customerName = orderConfirmation.getCustomerResponse().getFirstName() + " "+orderConfirmation.getCustomerResponse().getLastName();
        emailService.sendOrderSucessFullEmail(orderConfirmation.getCustomerResponse().getEmail(),customerName,
                orderConfirmation.getTotalAmount(), orderConfirmation.getReference(),orderConfirmation.getProductPuchaseResponses());


    }


}
