package com.jung.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    @Test
    void given_nullDocument_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new Document(null));
    }

    @Test
    void given_blankDocument_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Document(""));
    }

}