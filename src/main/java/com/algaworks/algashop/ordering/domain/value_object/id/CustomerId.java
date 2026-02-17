package com.algaworks.algashop.ordering.domain.value_object.id;

import com.algaworks.algashop.ordering.domain.utility.IdGenerator;

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
