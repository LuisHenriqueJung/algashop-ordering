package com.algaworks.algashop.ordering.domain.value_object;

import java.util.Objects;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.DOCUMENT_IS_BLANK;

public record Document(String value) {
    public  Document(String value) {
        Objects.requireNonNull(value);
        if(value.isEmpty()){
            throw new IllegalArgumentException(DOCUMENT_IS_BLANK);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
