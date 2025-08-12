package com.order.order.notification;


import com.order.order.dto.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class OrderNotificationProducer {
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;
    public void sendPaymentNotification(OrderConfirmation orderConfirmation){
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader(TOPIC,"order-topic").build();
        kafkaTemplate.send(message);
    }

}
