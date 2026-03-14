package com.jung.algashop.ordering.domain.valueobject;

import com.jung.algashop.ordering.domain.model.valueobject.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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