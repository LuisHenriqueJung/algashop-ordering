package com.jung.algashop.ordering.domain.model.valueobject;

import java.util.Objects;

public record ZipCode(String value) {

    public ZipCode {
        Objects.requireNonNull(value);
        if(value.isEmpty()){
            throw new IllegalArgumentException("Zip code must not be empty");
        }
        if (value.length() != 5) {
            throw new IllegalArgumentException("Zip code must have 5 characters");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
