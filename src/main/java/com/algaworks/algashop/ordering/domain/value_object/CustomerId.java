package com.algaworks.algashop.ordering.domain.value_object;

import com.algaworks.algashop.ordering.domain.utility.IdGenerator;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID id) {
    public CustomerId() {
        this(IdGenerator.generateTimeBasedUUID());
    }
    public CustomerId(UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
