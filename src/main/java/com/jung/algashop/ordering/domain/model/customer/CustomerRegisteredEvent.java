package com.jung.algashop.ordering.domain.model.customer;

import java.time.OffsetDateTime;

public record CustomerRegisteredEvent(Customer customer, OffsetDateTime registeredAt) {
}
