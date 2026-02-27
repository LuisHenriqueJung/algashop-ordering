package com.jung.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void given_negativeValue_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    void given_zeroAsValue_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Quantity(0));
    }

    @Test
    void given_quantityToSubtract_whenTryToSubtract_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(1).subtract(new Quantity(2)));
    }

    @Test
    void given_quantityToSubtract_whenTryToSubtract_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Quantity(1).subtract(new Quantity(1)));
    }

    @Test
    void given_quantityToAdd_whenTryToAdd_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(1).add(new Quantity(-1)));
    }

    @Test
    void given_quantityToAdd_whenTryToAdd_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Quantity(1).add(new Quantity(1)));
    }

}