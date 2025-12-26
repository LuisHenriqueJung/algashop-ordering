package com.algaworks.algashop.ordering.domain.value_object;

import java.time.LocalDate;
import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST;

public record BirthDate(LocalDate birthDate) {
    public BirthDate(LocalDate birthDate) {
        Objects.requireNonNull(birthDate);
        if(birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
        }
        this.birthDate = birthDate;
    }

    public Integer age() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    @Override
    public String toString() {
        return birthDate.getDayOfMonth() +" de " + birthDate.getMonth() + " de " + birthDate.getYear();
    }
}
