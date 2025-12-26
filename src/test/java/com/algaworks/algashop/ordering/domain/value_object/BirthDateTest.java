package com.algaworks.algashop.ordering.domain.value_object;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {
    
    @Test
    void given_null_birth_date_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    void given_invalid_birth_date_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(LocalDate.now().plusDays(1)));
    }

}