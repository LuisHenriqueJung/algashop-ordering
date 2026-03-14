package com.jung.algashop.ordering.domain.model.valueobject;

import com.jung.algashop.ordering.domain.model.exception.ErrorMessages;
import com.jung.algashop.ordering.domain.model.validator.FieldValidations;

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
