package com.jung.algashop.ordering.domain.valueobject;

import com.jung.algashop.ordering.domain.exception.ErrorMessages;
import com.jung.algashop.ordering.domain.validator.FieldValidations;

public record Phone(String value) {

    public Phone(String value) {
        FieldValidations.requiresValidPhone(value, ErrorMessages.VALIDATION_ERROR_PHONE_IS_INVALID);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
