package com.payment.notification;


import com.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentNotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;
    public void sendPaymentNotification(PaymentNotificationRequest paymentNotificationRequest){
        Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(paymentNotificationRequest)
                .setHeader(TOPIC,"payment-topic").build();
        kafkaTemplate.send(message);
    }

}
