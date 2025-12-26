package com.algaworks.algashop.ordering.domain.value_object;

import com.algaworks.algashop.ordering.domain.exception.ErrorMessages;
import com.algaworks.algashop.ordering.domain.validator.FieldValidations;

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
