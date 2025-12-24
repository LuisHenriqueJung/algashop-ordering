package com.algaworks.algashop.ordering;

import com.algaworks.algashop.ordering.domain.entity.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CustomerTest {

    @Test
    public void testCustomer() {
        Customer customer = new Customer(
                UUID.randomUUID(),
                "John Doe",
                LocalDate.of(1991,7,6),
                "john.doe@example.com",
                "1234567890",
                "1234567890",
                true,
                OffsetDateTime.now()
        );
        customer.addLoyaltyPoints(10);
    }
}