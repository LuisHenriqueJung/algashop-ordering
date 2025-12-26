package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.exception.LoyaltyPointsShouldBePositiveException;
import com.algaworks.algashop.ordering.domain.value_object.*;
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
                            new CustomerId(),
                            new FullName("John", "Doe"),
                            new BirthDate(LocalDate.of(2000, 1, 1)),
                            new Email("invalid-email"),
                            new Phone("12345678901"),
                            new Document("12345678901"),
                            true,
                            OffsetDateTime.now()
                    );

                });
    }

    @Test
    void given_invalid_email_whenTryToChange_should_throw_exception() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                new Email("luis@rsdata.inf.br"),
                new Phone("12345678901"),
                new Document("12345678901"),
                true,
                OffsetDateTime.now()
        );
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail(new Email("invalid-email"));

                });
    }

    @Test
    void given_unarchivedCusomer_whenArchive_ShouldAninymize() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                new Email("luis@rsdata.inf.br"),
                new Phone("12345678901"),
                new Document("12345678901"),
                true,
                OffsetDateTime.now()
        );
        customer.archive();
        Assertions.assertWith(customer,
                c -> assertThat(c.fullName().firstName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email()).isNotEqualTo(new Email("luis@rsdata.inf.br")),
                c -> assertThat(c.phone()).isEqualTo(new Phone("000-000-0000")),
                c -> assertThat(c.document().value()).isEqualTo("000-00-0000"),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                c -> assertThat(c.archivedAt()).isNotNull(),
                c -> assertThat(c.loyaltyPoints().value()).isZero()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                new Email("luis@rsdata.inf.br"),
                new Phone("12345678901"),
                new Document("12345678901"),
                true,
                OffsetDateTime.now()
        );
        customer.archive();
        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archive);
        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeEmail(new Email("cannotChange@mail.com")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changePhone(new Phone("9999999999")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);

    }

    @Test
    void given_nonPositiveLoyaltyPoints_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                new BirthDate(LocalDate.of(2000, 1, 1)),
                new Email("luis@rsdata.inf.br"),
                new Phone("12345678901"),
                new Document("12345678901"),
                true,
                OffsetDateTime.now()
        );
        customer.addLoyaltyPoints(new LoyaltyPoints(10));
        Assertions.assertThat(customer.loyaltyPoints().value()).isEqualTo(10);
        customer.addLoyaltyPoints(new LoyaltyPoints(20));
        Assertions.assertThat(customer.loyaltyPoints().value()).isEqualTo(30);
        Assertions.assertThatExceptionOfType(LoyaltyPointsShouldBePositiveException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(-1)));
        Assertions.assertThatExceptionOfType(LoyaltyPointsShouldBePositiveException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(0)));
    }

}