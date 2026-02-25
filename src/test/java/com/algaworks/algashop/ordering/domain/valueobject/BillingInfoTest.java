package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingInfoTest {

    @Test
    void given_nullFullName_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new BillingInfo(null, new Document("04012211056"), new Phone("11999999999"), new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void given_nullDocument_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new BillingInfo(new FullName("John", "Doe"), null, new Phone("11999999999"), new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void given_nullPhone_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new BillingInfo(new FullName("John", "Doe"),  new Document("04012211056"),null, new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void given_nullAdress_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new BillingInfo(new FullName("John", "Doe"),  new Document("04012211056"), new Phone("11999999999"), null));
    }

}