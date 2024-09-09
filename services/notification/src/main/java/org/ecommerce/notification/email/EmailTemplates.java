package org.ecommerce.notification.email;

import lombok.Getter;

@Getter
public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully transferred"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order successfully transferred");

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
