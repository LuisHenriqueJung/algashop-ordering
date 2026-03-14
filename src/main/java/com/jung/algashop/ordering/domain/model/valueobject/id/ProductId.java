package com.jung.algashop.ordering.domain.model.valueobject.id;

import com.jung.algashop.ordering.domain.model.utility.IdGenerator;

import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID id) {

    public ProductId {
        Objects.requireNonNull(id);
    }

    public ProductId() {
        this(IdGenerator.generateTimeBasedUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
