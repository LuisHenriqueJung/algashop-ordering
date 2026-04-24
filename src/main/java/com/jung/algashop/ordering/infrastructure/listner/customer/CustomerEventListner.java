package com.jung.algashop.ordering.infrastructure.listner.customer;

import com.jung.algashop.ordering.application.customer.loyaltypoints.CustomerLoyaltyPointsApplicationService;
import com.jung.algashop.ordering.application.customer.notification.CustomerNotificationService;
import com.jung.algashop.ordering.domain.model.customer.CustomerArchivedEvent;
import com.jung.algashop.ordering.domain.model.customer.CustomerRegisteredEvent;
import com.jung.algashop.ordering.domain.model.order.OrderReadyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerEventListner {

    private final CustomerNotificationService customerNotificationService;

    private final CustomerLoyaltyPointsApplicationService customerLoyaltyPointsApplicationService;
    @EventListener
    public void handleCustomerRegisteredEvent(CustomerRegisteredEvent event) {
        log.info("Customer archived: {}", event.customerId().value());
        CustomerNotificationService.NotifyNewRegistrationInput input = new CustomerNotificationService.NotifyNewRegistrationInput(
                event.customerId().value(),
                event.fullName().firstName(),
                event.email().value()
        );

        customerNotificationService.notifyNewRegistration(input);
    }

    @EventListener
    public void handleCustomerArchivedEvent(CustomerArchivedEvent event) {
        log.info("Customer archived: {}", event.customerId());
    }

    @EventListener
    public void lesten(OrderReadyEvent event) {
        log.info("Order ready: {}", event.orderId());
        customerLoyaltyPointsApplicationService.addLoyaltyPoints(event.customerId().value(),
                event.orderId().toString());
    }



}
