package com.jung.algashop.ordering.domain.model.valueobject.id;

import com.jung.algashop.ordering.domain.model.utility.IdGenerator;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID id) {

    public CustomerId {
       Objects.requireNonNull(id);
    }

    public CustomerId() {
        this(IdGenerator.generateTimeBasedUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
