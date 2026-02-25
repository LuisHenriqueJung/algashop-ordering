package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Test
    void given_invalid_phone_whenTryToCreate_should_throw_exception() {
        assertThrows(IllegalArgumentException.class, () -> new Phone(""));
    }

    @Test
    void given_null_phone_whenTryToCreate_should_throw_exception() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

}