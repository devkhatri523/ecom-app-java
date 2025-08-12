package com.notification.notification.templates;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment Sucessfully Processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order Sucessfully Processed");

    @Getter
    private String template;
    @Getter
    private String subject;
    EmailTemplates(String template, String subject) {
        this.subject = subject;
        this.template = template;
    }

}
