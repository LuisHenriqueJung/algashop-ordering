package com.jung.algashop.ordering.domain.valueobject;

import com.jung.algashop.ordering.domain.exception.LoyaltyPointsShouldBePositiveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoyaltyPointsTest {

    @Test
    void given_nonPositiveValue_whenTryToCreate_shouldThrowException() {
        assertThrows(LoyaltyPointsShouldBePositiveException.class,
                () -> {
                    LoyaltyPoints loyaltyPoints = new LoyaltyPoints(-1);
        } );
    }

    @Test
    void given_positiveValue_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new LoyaltyPoints(1));
        assertDoesNotThrow(() -> new LoyaltyPoints(0));
    }

}