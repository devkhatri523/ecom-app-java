package com.notification.notification.entity;

import com.notification.notification.constant.NotificationType;
import com.notification.notification.model.payment.PaymentConfirmation;
import com.notification.notification.model.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Notification {
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private PaymentConfirmation paymentConfirmation;
}
