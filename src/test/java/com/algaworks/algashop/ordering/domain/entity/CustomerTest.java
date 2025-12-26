package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void given_unarchivedCusomer_whenArchive_ShouldAninymize(){
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
        customer.archive();
        Assertions.assertWith(customer,
                c -> assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email()).isNotEqualTo("luis@rsdata.inf.br"),
                c -> assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> assertThat(c.document()).isEqualTo("000-00-0000"),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                c -> assertThat(c.archivedAt()).isNotNull(),
                c -> assertThat(c.loyaltyPoints()).isZero()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException(){
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
        customer.archive();
        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archive);
        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(()->customer.changeEmail("cannotChange@mail.com"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(()->customer.changePhone("9999999999"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);

    }

}