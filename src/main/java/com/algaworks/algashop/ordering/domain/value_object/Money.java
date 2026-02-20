package com.algaworks.algashop.ordering.domain.value_object;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal value) implements Comparable<Money> {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public static final Money ZERO = new Money();

    public Money() {
        this(new BigDecimal(0));
    }

    public Money(String value) {
        this(new BigDecimal(value));
    }

    public Money {
        Objects.requireNonNull(value);
        value = value.setScale(SCALE, ROUNDING_MODE);
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(ErrorMessages.MONEY_SHOULD_BE_POSITIVE);
        }
    }

    public Money multiply(Quantity quantity) {
        Objects.requireNonNull(quantity);
        if (quantity.value() < 1) {
            throw new IllegalArgumentException(ErrorMessages.QUANTITY_SHOULD_BE_POSITIVE);
        }
        return new Money(this.value.multiply(new BigDecimal(quantity.value())));
    }

    public Money add(Money money) {
        Objects.requireNonNull(money);
        return new Money(this.value.add(money.value));
    }

    public Money divide(Money other, int scale) {
        Objects.requireNonNull(other);
        return new Money(this.value.divide(other.value, scale, ROUNDING_MODE));
    }

    public Money divide(Money other) {
        return divide(other, 2);
    }

    public Money divide(int divisor) {
        return divide(BigDecimal.valueOf(divisor));
    }

    public Money divide(BigDecimal divisor) {
        Objects.requireNonNull(divisor);
        return new Money(this.value.divide(divisor, SCALE, ROUNDING_MODE));
    }

    @Override
    public int compareTo(Money o) {
        Objects.requireNonNull(o);
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
