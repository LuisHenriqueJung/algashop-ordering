package com.jung.algashop.ordering.domain.model.valueobject;

import com.jung.algashop.ordering.domain.model.validator.FieldValidations;

import static com.jung.algashop.ordering.domain.model.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;

public record Email(String value) {
    public Email(String value) {
        FieldValidations.requiresValidEmail(value,VALIDATION_ERROR_EMAIL_IS_INVALID);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
