package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.exception.LoyaltyPointsShouldBePositiveException;

import java.util.Objects;

public record LoyaltyPoints(Integer value) implements Comparable<LoyaltyPoints> {
    public static final LoyaltyPoints ZERO = new LoyaltyPoints(0);
    public LoyaltyPoints() {
        this(0);
    }

    public LoyaltyPoints(Integer value) {
        Objects.requireNonNull(value);
        if(value < 0) {
            throw new LoyaltyPointsShouldBePositiveException();
        }
        this.value = value;
    }

    public void add(Integer points) {
        this.add(new LoyaltyPoints(points));
    }

    public LoyaltyPoints add(LoyaltyPoints loyaltyPoints) {
        Objects.requireNonNull(loyaltyPoints);

        if(loyaltyPoints.value() <= 0){
            throw new LoyaltyPointsShouldBePositiveException();
        }
        return new LoyaltyPoints(this.value + loyaltyPoints.value());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(LoyaltyPoints o) {
        return this.value().compareTo(o.value());
    }
}
