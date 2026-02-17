package com.algaworks.algashop.ordering.domain.value_object;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void given_invalidValue_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Money("-10.00"));
    }

    @Test
    void given_nullValue_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new Money((String) null));
    }

    @Test
    void given_validValue_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Money("10.00"));
    }

    @Test
    void given_valueWithMoreThanTwoDecimals_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Money("10.901040"));
    }

    @Test
    void given_valueWithMoreThanTwoDecimals_whenTryToCreate_shouldBeRounded() {
        Money money = new Money("10.901040");
        Assertions.assertWith(money,
                c -> assertThat(c.value()).isEqualTo(new BigDecimal("10.90")));
        Money money2 = new Money("10.988040");
        Assertions.assertWith(money2,
                c -> assertThat(c.value()).isEqualTo(new BigDecimal("10.99")));
    }

    @Test
    void given_quantityToMultiply_whenTryToMultiply_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Money("10.00").multiply(new Quantity(-1)));
    }

    @Test
    void given_quantityToMultiply_whenTryToMultiply_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Money("10.00").multiply(new Quantity(1)));
    }

    @Test
    void given_quantityToMultiply_whenTryToMultiply_shouldBeCorrect(){
        Money money = new Money("10.00");
        Quantity quantity = new Quantity(2);
        Money result = money.multiply(quantity);
        Assertions.assertWith(result,
                c -> assertThat(c.value()).isEqualTo(new BigDecimal("20.00")));
    }

}