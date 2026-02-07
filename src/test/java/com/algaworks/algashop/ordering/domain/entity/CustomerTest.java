package com.algaworks.algashop.ordering.domain.entity;


import com.algaworks.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algaworks.algashop.ordering.domain.exception.LoyaltyPointsShouldBePositiveException;
import com.algaworks.algashop.ordering.domain.value_object.Email;
import com.algaworks.algashop.ordering.domain.value_object.LoyaltyPoints;
import com.algaworks.algashop.ordering.domain.value_object.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void given_invalid_email_whenTryToCreate_should_throw_exception() {
        Customer customer = CustomerTestDataBuilder.brandNewCustomer().build();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail(new Email("invalid-email"));
                });
    }

    @Test
    void given_invalid_email_whenTryToChange_should_throw_exception() {
        Customer customer = CustomerTestDataBuilder.existingCustomerBuilder().build();
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail(new Email("invalid-email"));

                });
    }

    @Test
    void given_unarchivedCusomer_whenArchive_ShouldAninymize() {
        Customer customer = CustomerTestDataBuilder.existingCustomerBuilder().build();
        customer.archive();
        Assertions.assertWith(customer,
                c -> assertThat(c.fullName().firstName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email()).isNotEqualTo(new Email("luis@rsdata.inf.br")),
                c -> assertThat(c.phone()).isEqualTo(new Phone("000-000-0000")),
                c -> assertThat(c.document().value()).isEqualTo("000-00-0000"),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse(),
                c -> assertThat(c.archivedAt()).isNotNull(),
                c -> assertThat(c.loyaltyPoints().value()).isZero(),
                c -> assertThat(c.address().number()).isEqualTo("Anon")
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = CustomerTestDataBuilder.existingAnonymizedCustomer().build();
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
        Customer customer = CustomerTestDataBuilder.existingCustomerBuilder().build();
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