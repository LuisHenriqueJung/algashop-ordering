package com.jung.algashop.ordering.domain.valueobject.id;

import com.jung.algashop.ordering.domain.utility.IdGenerator;

import java.util.Objects;

public record ShoppingCartId(String value) {
    public ShoppingCartId {
        Objects.requireNonNull(value);
    }

    public ShoppingCartId() {
        this(IdGenerator.generateTSID().toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
