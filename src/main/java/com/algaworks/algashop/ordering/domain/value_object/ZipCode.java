package com.algaworks.algashop.ordering.domain.value_object;

import java.util.Objects;

public record ZipCode(String zipCode) {

    public ZipCode {
        Objects.requireNonNull(zipCode);
        if(zipCode.isEmpty()){
            throw new IllegalArgumentException("Zip code must not be empty");
        }
        if (zipCode.length() != 5) {
            throw new IllegalArgumentException("Zip code must have 5 characters");
        }
    }

    @Override
    public String toString() {
        return zipCode;
    }
}
