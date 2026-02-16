package com.algaworks.algashop.ordering.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShippingInfoTest {

    @Test
    void shouldNotCreateBillingInfoWithNullFullName() {
        assertThrows(NullPointerException.class, () -> new ShippingInfo(null, new Document("04012211056"), new Phone("11999999999"), new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void shouldNotCreateBillingInfoWithNullDocument() {
        assertThrows(NullPointerException.class, () -> new ShippingInfo(new FullName("John", "Doe"), null, new Phone("11999999999"), new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void shouldNotCreateBillingInfoWithNullPhone() {
        assertThrows(NullPointerException.class, () -> new ShippingInfo(new FullName("John", "Doe"),  new Document("04012211056"),null, new Address("Rua Teste", "123", "Bairro Teste", "Complemento Teste", new ZipCode("04011"), "Sao Paulo", "SP", "Brasil")));
    }

    @Test
    void shouldNotCreateBillingInfoWithNullAdress() {
        assertThrows(NullPointerException.class, () -> new ShippingInfo(new FullName("John", "Doe"),  new Document("04012211056"), new Phone("11999999999"), null));
    }

}