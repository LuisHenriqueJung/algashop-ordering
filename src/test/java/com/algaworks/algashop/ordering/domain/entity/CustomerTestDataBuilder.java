package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.value_object.*;
import com.algaworks.algashop.ordering.domain.value_object.id.CustomerId;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    public static Customer.BrandNewCustomerBuilder brandNewCustomer() {
        return Customer.brandNew()
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(2000, 1, 1)))
                .email(new Email("luis@rsdata.inf.br"))
                .phone(new Phone("12345678901"))
                .document(new Document("12345678901"))
                .promotionNotificationsAllowed(true)
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("122")
                        .neighborhood("Neighborhood")
                        .complement("Complement")
                        .zipCode(new ZipCode("12345"))
                        .city("City")
                        .state("State")
                        .country("Country")
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingCustomerBuilder(){
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("John", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(2000, 1, 1)))
                .email(new Email("luis@rsdata.inf.br"))
                .phone(new Phone("12345678901"))
                .document(new Document("12345678901"))
                .promotionNotificationsAllowed(true)
                .archived(false)
                .registeredAt(OffsetDateTime.now())
                .loyaltyPoints(LoyaltyPoints.ZERO)
                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("122")
                        .neighborhood("Neighborhood")
                        .complement("Complement")
                        .zipCode(new ZipCode("12345"))
                        .city("City")
                        .state("State")
                        .country("Country")
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingAnonymizedCustomer(){
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("An", "Doe"))
                .birthDate(new BirthDate(LocalDate.of(2000, 1, 1)))
                .email(new Email("luis@rsdata.inf.br"))
                .phone(new Phone("12345678901"))
                .document(new Document("12345678901"))
                .promotionNotificationsAllowed(true)
                .archived(true)
                .registeredAt(OffsetDateTime.now())
                .loyaltyPoints(LoyaltyPoints.ZERO)

                .address(Address.builder()
                        .street("Bourbon Street")
                        .number("122")
                        .neighborhood("Neighborhood")
                        .complement("Complement")
                        .zipCode(new ZipCode("12345"))
                        .city("City")
                        .state("State")
                        .country("Country")
                        .build());
    }
}
