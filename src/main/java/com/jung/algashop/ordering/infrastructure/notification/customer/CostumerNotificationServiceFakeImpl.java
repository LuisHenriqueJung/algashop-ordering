package com.jung.algashop.ordering.infrastructure.notification.customer;

import com.jung.algashop.ordering.application.customer.notification.CustomerNotificationService;
import com.jung.algashop.ordering.domain.model.customer.Customer;
import com.jung.algashop.ordering.domain.model.customer.CustomerId;
import com.jung.algashop.ordering.domain.model.customer.CustomerNotFoundException;
import com.jung.algashop.ordering.domain.model.customer.Customers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostumerNotificationServiceFakeImpl implements CustomerNotificationService {

    private final Customers customers;

    @Override
    public void notifyNewRegistration(NotifyNewRegistrationInput input) {
        Customer customer = customers.ofId(new CustomerId(input.customerId())).orElseThrow(
                CustomerNotFoundException::new
        );
        log.info("Customer {} notified", customer.id());

    }
}
