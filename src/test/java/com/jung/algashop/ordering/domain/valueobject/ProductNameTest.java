package com.jung.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductNameTest {

    @Test
    void given_nullName_whenTryToCreate_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> new ProductName(null));
    }

    @Test
    void given_blankName_whenTryToCreate_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new ProductName(""));
    }

    @Test
    void given_validName_whenTryToCreate_shouldNotThrowException() {
        assertDoesNotThrow(() -> new ProductName("valid-name"));
    }

    @Test
    void given_nameWithLeadingAndTrailingSpaces_whenTryToCreate_shouldTrimName() {
        ProductName productName = new ProductName(" valid-name ");
        assertEquals("valid-name", productName.name());
    }

}