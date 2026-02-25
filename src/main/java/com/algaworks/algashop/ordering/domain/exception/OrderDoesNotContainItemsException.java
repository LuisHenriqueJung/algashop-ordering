package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderItemId;

public class OrderDoesNotContainItemsException extends DomainException{
    public OrderDoesNotContainItemsException(OrderId id, OrderItemId itemId) {
        super(String.format(ErrorMessages.ORDER_DOES_NOT_CONTAIN_ITEM,  id, itemId));
    }
}
