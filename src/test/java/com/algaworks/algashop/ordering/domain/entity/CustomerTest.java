package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class CustomerTest {

    @Test
    void given_invalid_email_whenTryToCreate_should_throw_exception() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Customer(
                            IdGenerator.generateTimeBasedUUID(),
                            "John Doe",
                            LocalDate.of(2000, 1, 1),
                            "invalid-email",
                            "12345678901",
                            "12345678901",
                            true,
                            OffsetDateTime.now()
                    );

                });
    }

    @Test
    void given_invalid_email_whenTryToChange_should_throw_exception() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(2000, 1, 1),
                "luis@rsdata.inf.br",
                "12345678901",
                "12345678901",
                true,
                OffsetDateTime.now()
        );
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail("invalid-email");

                });
    }
}