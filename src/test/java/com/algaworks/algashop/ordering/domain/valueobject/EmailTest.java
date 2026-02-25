package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void given_invalidEmail_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Email("invalid-email"));
    }

    @Test
    void given_validEmail_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Email("valid-email@domain.com"));
    }

}