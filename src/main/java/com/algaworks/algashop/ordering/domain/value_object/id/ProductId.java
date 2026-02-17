package com.algaworks.algashop.ordering.domain.value_object.id;

import com.algaworks.algashop.ordering.domain.utility.IdGenerator;

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
