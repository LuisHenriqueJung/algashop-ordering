package com.jung.algashop.ordering.application.order.notification;

import java.util.UUID;

public interface OrderNotificationService {

    record NotifyNewRegistrationInput(UUID orderId, String firstName, String email) {
    }
}
