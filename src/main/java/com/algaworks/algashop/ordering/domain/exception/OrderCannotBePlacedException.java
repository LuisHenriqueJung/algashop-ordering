package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.value_object.id.OrderId;

public class OrderCannotBePlacedException extends DomainException {
    public OrderCannotBePlacedException(OrderId id) {
        super(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_ITEMS, id));
    }
}
