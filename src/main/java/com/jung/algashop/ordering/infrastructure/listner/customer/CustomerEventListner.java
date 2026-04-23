package com.jung.algashop.ordering.infrastructure.listner.customer;

import com.jung.algashop.ordering.application.customer.notification.CustomerNotificationService;
import com.jung.algashop.ordering.domain.model.customer.CustomerArchivedEvent;
import com.jung.algashop.ordering.domain.model.customer.CustomerRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerEventListner {

    private final CustomerNotificationService customerNotificationService;

    @EventListener
    public void handleCustomerRegisteredEvent(CustomerRegisteredEvent event) {
        log.info("Customer archived: {}", event.customer().id().value());
        CustomerNotificationService.NotifyNewRegistrationInput input = new CustomerNotificationService.NotifyNewRegistrationInput(
                event.customer().id().value(),
                event.customer().fullName().firstName(),
                event.customer().email().value()
        );

        customerNotificationService.notifyNewRegistration(input);
    }

    @EventListener
    public void handleCustomerArchivedEvent(CustomerArchivedEvent event) {
        log.info("Customer archived: {}", event.customerId());
    }



}
