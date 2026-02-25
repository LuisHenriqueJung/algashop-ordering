package com.algaworks.algashop.ordering.domain.valueobject;

public record Quantity(int value) implements Comparable<Quantity> {

    public static final Quantity ZERO = new Quantity();
    public static final Quantity ONE = new Quantity(1);

    public Quantity() {
        this(0);
    }

    public Quantity {
        if (value < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    public Quantity add(Quantity quantity) {
        return new Quantity(this.value + quantity.value);
    }

    public Quantity subtract(Quantity quantity) {
        return new Quantity(this.value - quantity.value);
    }


    @Override
    public int compareTo(Quantity o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
