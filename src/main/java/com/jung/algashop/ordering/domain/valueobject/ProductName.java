package com.jung.algashop.ordering.domain.valueobject;

import com.jung.algashop.ordering.domain.validator.FieldValidations;

import java.util.Objects;

public record ProductName(String name) implements Comparable<ProductName> {

    public ProductName {
        Objects.requireNonNull(name);
        name = name.trim();
        FieldValidations.requiresNonBlank(name);
    }

    @Override
    public String toString() {
        return this.name();
    }

    @Override
    public int compareTo(ProductName o) {
        return this.name.compareTo(o.name);
    }
}
