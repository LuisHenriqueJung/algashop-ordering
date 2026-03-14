package com.jung.algashop.ordering.domain.model.valueobject.id;

import com.jung.algashop.ordering.domain.model.utility.IdGenerator;

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
