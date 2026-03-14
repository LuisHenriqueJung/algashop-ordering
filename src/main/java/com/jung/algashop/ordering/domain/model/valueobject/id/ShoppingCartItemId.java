package com.jung.algashop.ordering.domain.model.valueobject.id;

import com.jung.algashop.ordering.domain.model.utility.IdGenerator;

import java.util.Objects;

public record ShoppingCartItemId(String value) {
    public ShoppingCartItemId {
        Objects.requireNonNull(value);
    }

    public ShoppingCartItemId() {
        this(IdGenerator.generateTSID().toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
