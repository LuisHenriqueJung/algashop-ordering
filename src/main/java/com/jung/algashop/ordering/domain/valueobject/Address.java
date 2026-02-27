package com.jung.algashop.ordering.domain.valueobject;

import com.jung.algashop.ordering.domain.validator.FieldValidations;
import lombok.Builder;

import java.util.Objects;
public record Address(String street, String number, String neighborhood, String complement, ZipCode zipCode,
                      String city, String state, String country) {

    @Builder(toBuilder = true)
    public Address {
        FieldValidations.requiresNonBlank(street);
        FieldValidations.requiresNonBlank(number);
        FieldValidations.requiresNonBlank(neighborhood);
        FieldValidations.requiresNonBlank(city);
        FieldValidations.requiresNonBlank(state);
        Objects.requireNonNull(zipCode);
    }
}
